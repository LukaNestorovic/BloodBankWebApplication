package com.isa.centarzatransfuzijukrvi.service

import com.isa.centarzatransfuzijukrvi.model.Appointment
import com.isa.centarzatransfuzijukrvi.model.Center
import com.isa.centarzatransfuzijukrvi.model.dto.AppointmentAdminDTO
import com.isa.centarzatransfuzijukrvi.model.dto.AppointmentCenterUserDTO
import com.isa.centarzatransfuzijukrvi.model.dto.AppointmentFullDTO
import com.isa.centarzatransfuzijukrvi.repository.AppointmentRepository
import com.isa.centarzatransfuzijukrvi.repository.CenterRepository
import org.hibernate.type.PrimitiveCharacterArrayNClobType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.Date

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

    fun findCentersFreeAtTime(start:Date): List<AppointmentCenterUserDTO>{
        var retVal: ArrayList<AppointmentCenterUserDTO> = ArrayList()
        val centers = centerRepository.findAll()
        for(center in centers){
            val end = Date(start.time+1000*60*60)
            val appointment = findAppointmentsWithoutUser(start,end,center.name)
            if(appointment.id !=0){
                if(appointment.id==-1){
                    retVal.add(AppointmentCenterUserDTO(center.name,center.address,start,end))
                }else{
                    retVal.add(AppointmentCenterUserDTO(center.name,center.address,appointment.time,Date(appointment.time.time+1000*60*60)))
                }
            }
        }
        return retVal
    }

    fun findAppointmentsWithoutUser(start: Date, end: Date, centerName: String): Appointment{
        val allAppointments = appointmentRepository.findAll()
        for(scheduled in allAppointments){
            val endTime = Date(scheduled.time.time+1000*60*60-1)
            if(scheduled.center?.name.equals(centerName)){
                println("FIRST " + start.toString() + '<' + scheduled.time.toString() + "<" + end.toString() + "is " + (scheduled.time > start && scheduled.time < end))
                println("SECOND " + start.toString() + '<' + endTime.toString() + "<" + end.toString() + "is " + (endTime > start && endTime < end))
                if(((scheduled.time >= start && scheduled.time < end) || (endTime > start && endTime < end))) {
                    if (scheduled.donor != null) {
                        return scheduled;
                    }
                    return Appointment(0, time = Date(), null, null, null);
                }
            }
        }
        return Appointment(-1, time = Date(),null,null,null);
    }
}