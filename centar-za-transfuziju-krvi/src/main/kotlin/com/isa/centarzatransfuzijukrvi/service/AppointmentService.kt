package com.isa.centarzatransfuzijukrvi.service

import com.isa.centarzatransfuzijukrvi.model.Appointment
import com.isa.centarzatransfuzijukrvi.model.RegisteredUser
import com.isa.centarzatransfuzijukrvi.model.dto.AppointmentAdminDTO
import com.isa.centarzatransfuzijukrvi.model.dto.AppointmentDTO
import com.isa.centarzatransfuzijukrvi.model.dto.AppointmentFullDTO
import com.isa.centarzatransfuzijukrvi.repository.AppointmentRepository
import com.isa.centarzatransfuzijukrvi.repository.CenterRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*
import kotlin.collections.ArrayList

@Service
class AppointmentService(@Autowired val appointmentRepository: AppointmentRepository, @Autowired val centerRepository: CenterRepository) {
    fun create(toSchedule: AppointmentAdminDTO) : Appointment?{
        val center = centerRepository.findByName(toSchedule.centerName).get()
        val endDate = Date(toSchedule.date.time+1000*60*60)

//        println("START:" + toSchedule.date::class.simpleName + " " + toSchedule.date)
//        println("END:" + endDate::class.simpleName + " " + endDate)

        val overlap = this.findOverlappingAppointments(toSchedule.date,endDate,toSchedule.centerName)
        if(overlap!=null) {
            return null;
        }
        val appointment = Appointment(time = toSchedule.date, center = center, doctor = null, donor = null,)
        return appointmentRepository.save(appointment)
    }
    fun findOverlappingAppointments(start: Date, end: Date, centerName: String): Appointment?{
        val allAppointments = appointmentRepository.findAll()
        for(scheduled in allAppointments){
            val endTime = Date(scheduled.time.time+1000*60*60-1)
            if(scheduled.center?.name.equals(centerName) &&
                ((scheduled.time > start && scheduled.time < end) ||
                (endTime > start && endTime < end))){
                    return Appointment(0, time = Date(),null,null,null);
            }
        }
        return null;
    }

    fun findAll() : List<AppointmentFullDTO>? {
        var retVal: ArrayList<AppointmentFullDTO> = ArrayList()
        for(app in appointmentRepository.findAll()){
            println("FOUND 1")
            retVal.add(AppointmentFullDTO(app.time,Date(
                app.time.time+1000*60*60),
                (app.donor?.name ?: "Empty") + " " +
                     (app.donor?.surname ?: "term") + "@" +
                      (app.center?.name ?: "ERR"),
                resource = app.center!!.name))
        }
        return retVal
    }

    fun findAllEmptyAppointments() : List<AppointmentDTO> {
        var termini: ArrayList<AppointmentDTO> = ArrayList()
        for(app in appointmentRepository.findAll()){
            if(app.donor == null)
                termini.add(AppointmentDTO(app.id, app.doctor?.name, app.center?.name, app.time.toString()))
        }
        return termini
    }

    fun updateAppointment(id: Int, pacijent: RegisteredUser) : Appointment {
        val termin = appointmentRepository.findById(id)
        termin.get().updateAppointment(pacijent)
        return appointmentRepository.save(termin.get())
    }

    fun deleteAppointment(id: Int) : Appointment{
        val termin = appointmentRepository.findById(id)
        termin.get().deleteAppointment()
        return appointmentRepository.save(termin.get())
    }

    fun findAppointmentsOfPatient(user: RegisteredUser) : List<AppointmentDTO> {
        var termini: ArrayList<AppointmentDTO> = ArrayList()
        for(app in appointmentRepository.findAll()){
            if(app.donor == user) {
                termini.add(AppointmentDTO(app.id, app.doctor?.name, app.center?.name, app.time.toString()))
            }
        }
        return termini
    }
}