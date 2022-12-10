package com.isa.centarzatransfuzijukrvi.repository

import com.isa.centarzatransfuzijukrvi.model.Appointment
import org.springframework.data.jpa.repository.JpaRepository

interface AppointmentRepository: JpaRepository<Appointment,Int> {
}