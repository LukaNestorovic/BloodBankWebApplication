package com.isa.centarzatransfuzijukrvi.repository

import com.isa.centarzatransfuzijukrvi.model.Appointment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AppointmentRepository: JpaRepository<Appointment,Int> {
//    @Query("SELECT a FROM Appointment a WHERE a.center.id = :id_center " +
//                                            "AND :start BETWEEN a.time AND a.time + interval 1 hour " +
//                                            "OR :end BETWEEN a.time AND a.time + interval 1 hour")
//    fun findOverlappingAppointments(@Param("start") start: Date,@Param("end") end: Date, @Param("id_center") id_center: Int): List<Appointment> ;
}