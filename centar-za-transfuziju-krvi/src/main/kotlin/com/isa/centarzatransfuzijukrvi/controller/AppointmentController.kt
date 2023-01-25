package com.isa.centarzatransfuzijukrvi.controller

import com.isa.centarzatransfuzijukrvi.model.Appointment
import com.isa.centarzatransfuzijukrvi.model.Center
import com.isa.centarzatransfuzijukrvi.model.DonorForm
import com.isa.centarzatransfuzijukrvi.model.dto.*
import com.isa.centarzatransfuzijukrvi.service.AppointmentService
import com.isa.centarzatransfuzijukrvi.service.DonorFormService
import com.isa.centarzatransfuzijukrvi.service.RegisteredUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*


@RestController
@RequestMapping(path = ["api"])
@CrossOrigin(origins=["*"])
class AppointmentController(@Autowired val appointmentService: AppointmentService, @Autowired val registeredUserService: RegisteredUserService, @Autowired val donorFormService: DonorFormService) {

    @PostMapping(path=["appointment/admin"], consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun createAdmin(@RequestBody unscheduledAppointment : AppointmentAdminDTO) : ResponseEntity<Appointment>{
        //println("REQUEST:" + unscheduledAppointment.centerName.toString() + " DATE:" + unscheduledAppointment.date.toString())
        val app = appointmentService.create(unscheduledAppointment)
        if(app!=null){
            if(app.id==-1){
                return ResponseEntity(app,HttpStatus.FORBIDDEN)
            }
            return ResponseEntity(app,HttpStatus.CREATED)
        }
        return return ResponseEntity(null,HttpStatus.NOT_ACCEPTABLE)
    }

    @PutMapping(path=["appointment/admin"], consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllAdmin(@RequestBody staff: AppointmentGetForStaffDTO): ResponseEntity<List<AppointmentFullDTO>?>{
        return ResponseEntity(appointmentService.findAll(staff.email),HttpStatus.OK)
    }


    @GetMapping(path = ["/appointments/{enable}/{role}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllAppointments(@PathVariable("enable") enable: String, @PathVariable("role") role: String): ResponseEntity<List<AppointmentDTO>> {
        if(role == "user" && enable == "true")
            return ResponseEntity(appointmentService.findAllEmptyAppointments(), HttpStatus.OK)
        else return ResponseEntity(appointmentService.findAllEmptyAppointments(), HttpStatus.UNAUTHORIZED)
    }

    @PutMapping(path = ["/appointment"], consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun updateAppointment(@RequestBody dto: UpdateDTO) : ResponseEntity<Appointment> {
        var pacijent = registeredUserService.findByEmail(dto.email)
        var forme : ArrayList<DonorForm> = ArrayList()
        var lista : ArrayList<Appointment> = ArrayList()

        for(form in donorFormService.findAll()){
            if(form.user == pacijent)
                forme.add(form)
        }
        if(forme.size == 1) {
            if (forme.get(0).question15 == "No") {
                for (app in appointmentService.findAllRepo()) {
                    if (app.donor == pacijent)
                        lista.add(app)
                }
                if (lista.size == 0) {
                    var povratna = appointmentService.updateAppointment(dto.id, pacijent)
                    return ResponseEntity(povratna, HttpStatus.OK)
                } else return ResponseEntity(null, HttpStatus.BAD_REQUEST)
            } else return ResponseEntity(null, HttpStatus.UNAUTHORIZED)
        }
        else return ResponseEntity(null, HttpStatus.NOT_FOUND)
    }

    @GetMapping(path = ["/scheduledappointment/{email}/{role}/{enable}"],produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAppointmentsOfPatient(@PathVariable("email") email: String, @PathVariable("role") role: String, @PathVariable("enable") enable: String): ResponseEntity<List<AppointmentDTO>> {
        if(role == "user" && enable == "true") {
            var pacijent = registeredUserService.findByEmail(email)
            return ResponseEntity(appointmentService.findAppointmentsOfPatient(pacijent), HttpStatus.OK)
        }
        else return ResponseEntity(null, HttpStatus.UNAUTHORIZED)
    }

    @GetMapping(path = ["/pastappointment/{email}/{role}/{enable}"],produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getPastAppointmentsOfPatient(@PathVariable("email") email: String, @PathVariable("role") role: String, @PathVariable("enable") enable: String): ResponseEntity<List<AppointmentDTO>> {
        if(role == "user" && enable == "true") {
            var pacijent = registeredUserService.findByEmail(email)
            return ResponseEntity(appointmentService.findPastAppointmentsOfPatient(pacijent), HttpStatus.OK)
        }
        else return ResponseEntity(null, HttpStatus.UNAUTHORIZED)
    }

    @PutMapping(path = ["/deleteappointment"], consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun deleteAppointment(@RequestBody dto: UpdateDTO) : ResponseEntity<Appointment> {
        var lista : ArrayList<Appointment> = ArrayList()
        var localDate = LocalDateTime.now()
        var datum : Date = Date.from(localDate.atZone(ZoneId.systemDefault()).toInstant())
        var pacijent = registeredUserService.findByEmail(dto.email)
        for (app in appointmentService.findAllRepo()) {
            if (app.donor == pacijent)
                lista.add(app)
        }
        if(lista.size == 1) {
            if((((lista.get(0).time.time - datum.time) / 1000) / 60) / 60 > 24){
                println(lista.get(0).time)
                println(datum)
                println((((lista.get(0).time.time - datum.time) / 1000) / 60) / 60)
                var povratna = appointmentService.deleteAppointment(dto.id)
                return ResponseEntity(povratna, HttpStatus.OK)
            }
            else return ResponseEntity(null, HttpStatus.UNAUTHORIZED)
        }
        else return ResponseEntity(null, HttpStatus.OK)
    }
    @PostMapping(path=["appointment/user"], consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun queryAppointments(@RequestBody query : AppointmentSearchUserDTO) : ResponseEntity<List<AppointmentCenterUserDTO>>{
        return ResponseEntity(appointmentService.findCentersFreeAtTime(query),HttpStatus.OK)
    }
    @PutMapping(path=["appointment/user"], consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun enrollAppointment(@RequestBody enroll : AppointmentEnrollDTO) : ResponseEntity<Appointment>{
        val app = appointmentService.enrollAppointment(enroll)
        if(app.id==-1)
            return ResponseEntity(null,HttpStatus.FORBIDDEN)
        return ResponseEntity(app,HttpStatus.CREATED)
    }
}