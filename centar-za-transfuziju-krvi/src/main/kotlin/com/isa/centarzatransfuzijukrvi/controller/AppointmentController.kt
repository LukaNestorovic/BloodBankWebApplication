package com.isa.centarzatransfuzijukrvi.controller

import com.isa.centarzatransfuzijukrvi.model.Appointment
import com.isa.centarzatransfuzijukrvi.model.dto.*
import com.isa.centarzatransfuzijukrvi.service.AppointmentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(path = ["api"])
@CrossOrigin(origins=["*"])
class AppointmentController(@Autowired val appointmentService: AppointmentService) {

    @PostMapping(path=["appointment/admin"], consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun createAdmin(@RequestBody unscheduledAppointment : AppointmentAdminDTO) : ResponseEntity<Appointment>{
        //println("REQUEST:" + unscheduledAppointment.centerName.toString() + " DATE:" + unscheduledAppointment.date.toString())
        return ResponseEntity(appointmentService.create(unscheduledAppointment),HttpStatus.CREATED)
    }

    @PutMapping(path=["appointment/admin"], consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getAllAdmin(@RequestBody staff: AppointmentGetForStaffDTO): ResponseEntity<List<AppointmentFullDTO>?>{
        return ResponseEntity(appointmentService.findAll(staff.email),HttpStatus.OK)
    }

    @PostMapping(path=["appointment/user"], consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun queryAppointments(@RequestBody query : AppointmentSearchUserDTO) : ResponseEntity<List<AppointmentCenterUserDTO>>{
        return ResponseEntity(appointmentService.findCentersFreeAtTime(query),HttpStatus.OK)
    }
    @PutMapping(path=["appointment/user"], consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun enrollAppointment(@RequestBody enroll : AppointmentEnrollDTO) : ResponseEntity<Appointment>{
        return ResponseEntity(appointmentService.enrollAppointment(enroll),HttpStatus.CREATED)
    }
}