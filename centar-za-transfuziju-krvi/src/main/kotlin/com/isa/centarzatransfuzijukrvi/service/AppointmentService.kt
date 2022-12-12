package com.isa.centarzatransfuzijukrvi.service

import com.isa.centarzatransfuzijukrvi.model.Appointment
import com.isa.centarzatransfuzijukrvi.model.dto.AppointmentAdminDTO
import com.isa.centarzatransfuzijukrvi.repository.AppointmentRepository
import com.isa.centarzatransfuzijukrvi.repository.CenterRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AppointmentService(@Autowired val appointmentRepository: AppointmentRepository, @Autowired val centerRepository: CenterRepository) {
    fun create(unscheduledAppointment: AppointmentAdminDTO) : Appointment{
        val center = centerRepository.findById(unscheduledAppointment.centerId).get()
        val appointment = Appointment(time = unscheduledAppointment.date, center = center, doctor = null, donor = null,)
        return appointmentRepository.save(appointment)
    }
}