package com.isa.centarzatransfuzijukrvi.controller

import com.isa.centarzatransfuzijukrvi.model.Appointment
import com.isa.centarzatransfuzijukrvi.model.dto.AppointmentAdminDTO
import com.isa.centarzatransfuzijukrvi.model.dto.AppointmentDTO
import com.isa.centarzatransfuzijukrvi.model.dto.AppointmentFullDTO
import com.isa.centarzatransfuzijukrvi.model.dto.UpdateDTO
import com.isa.centarzatransfuzijukrvi.service.AppointmentService
import com.isa.centarzatransfuzijukrvi.service.RegisteredUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(path = ["api"])
@CrossOrigin(origins=["*"])
class AppointmentController(@Autowired val appointmentService: AppointmentService, @Autowired val registeredUserService: RegisteredUserService) {

    @PostMapping(path=["appointment/admin"], consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun createAdmin(@RequestBody unscheduledAppointment : AppointmentAdminDTO) : ResponseEntity<Appointment>{
        //println("REQUEST:" + unscheduledAppointment.centerName.toString() + " DATE:" + unscheduledAppointment.date.toString())
        return ResponseEntity(appointmentService.create(unscheduledAppointment),HttpStatus.CREATED)
    }

    @GetMapping(path=["appointment/admin"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllAdmin(): ResponseEntity<List<AppointmentFullDTO>?> = ResponseEntity(appointmentService.findAll(),HttpStatus.OK)

    @GetMapping(path = ["/appointments"],produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllAppointments(): ResponseEntity<List<AppointmentDTO>> = ResponseEntity(appointmentService.findAllEmptyAppointments(), HttpStatus.OK)

    @PutMapping(path = ["/appointment"], consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun updateAppointment(@RequestBody dto: UpdateDTO) : ResponseEntity<Appointment> {
        var pacijent = registeredUserService.findByEmail(dto.email)
        println(dto.email)
        var povratna = appointmentService.updateAppointment(dto.id, pacijent)
        return ResponseEntity(povratna, HttpStatus.ACCEPTED)
    }

}