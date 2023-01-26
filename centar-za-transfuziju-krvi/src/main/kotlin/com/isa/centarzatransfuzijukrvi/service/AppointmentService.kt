package com.isa.centarzatransfuzijukrvi.service

import com.isa.centarzatransfuzijukrvi.model.Appointment
import com.isa.centarzatransfuzijukrvi.model.RegisteredUser
import com.isa.centarzatransfuzijukrvi.model.dto.AppointmentAdminDTO
import com.isa.centarzatransfuzijukrvi.model.dto.AppointmentDTO
import com.isa.centarzatransfuzijukrvi.model.dto.AppointmentFullDTO
import com.isa.centarzatransfuzijukrvi.model.Center
import com.isa.centarzatransfuzijukrvi.model.Staff
import com.isa.centarzatransfuzijukrvi.model.dto.*
import com.isa.centarzatransfuzijukrvi.repository.AppointmentRepository
import com.isa.centarzatransfuzijukrvi.repository.CenterRepository
import com.isa.centarzatransfuzijukrvi.repository.RegisteredUserRepository
import com.isa.centarzatransfuzijukrvi.repository.StaffRepository
import org.hibernate.type.PrimitiveCharacterArrayNClobType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Isolation
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import java.util.*
import kotlin.collections.ArrayList
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date

@Service
class AppointmentService(@Autowired val appointmentRepository: AppointmentRepository, @Autowired val centerRepository: CenterRepository,
                         @Autowired val userRepository: RegisteredUserRepository, @Autowired val emailService: EmailService,@Autowired val staffRepository: StaffRepository) {
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    fun create(toSchedule: AppointmentAdminDTO) : Appointment?{
        try{
            val staff: Staff = staffRepository.findOneByEmail(toSchedule.email)
            val center = centerRepository.findByName(toSchedule.centerName).get()
            val endDate = Date(toSchedule.date.time+1000*60*60)
            val overlap = this.findOverlappingAppointments(toSchedule.date,endDate,toSchedule.centerName)
            if(overlap!=null)
                return null;

            return if(center == staff.center && staff.role == "Doctor"){
                appointmentRepository.save(Appointment(time = toSchedule.date, center = center, doctor = staff, donor = null,))
            }else{
                appointmentRepository.save(Appointment(time = toSchedule.date, center = center, doctor = null, donor = null,))
            }
        }catch (e: Exception){
            return Appointment(-1,Date(),null,null,null);
        }
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

    fun findAll(email: String) : List<AppointmentFullDTO>? {
        var retVal: ArrayList<AppointmentFullDTO> = ArrayList()
        for(app in appointmentRepository.findAll()){
            //println("MAIL:" + app.doctor?.email + " " + email)
            if(app.doctor?.email.equals(email)){
                retVal.add(AppointmentFullDTO(app.time,Date(
                    app.time.time+1000*60*60),
                    (app.donor?.name ?: "Empty") + " " +
                            (app.donor?.surname ?: "term") + "@" +
                            (app.center?.name ?: "ERR"),
                    resource = app.center!!.name))
            }
        }
        return retVal
    }

    fun findAllRepo() : List<Appointment> = appointmentRepository.findAll()

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
        var trenutno = LocalDate.now()
        for(app in appointmentRepository.findAll()){
            if(app.donor == user) {
                var vreme = app.time.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                if(trenutno < vreme)
                    termini.add(AppointmentDTO(app.id, app.doctor?.name, app.center?.name, app.time.toString()))
            }
        }
        return termini
    }

    fun findPastAppointmentsOfPatient(user: RegisteredUser) : List<AppointmentDTO> {
        var termini: ArrayList<AppointmentDTO> = ArrayList()
        var trenutno = LocalDate.now()
        for(app in appointmentRepository.findAll()){
            if(app.donor == user) {
                var vreme = app.time.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                if(trenutno > vreme)
                    termini.add(AppointmentDTO(app.id, app.doctor?.name, app.center?.name, app.time.toString()))
            }
        }
        return termini
    }

    fun findCentersFreeAtTime(query: AppointmentSearchUserDTO): List<AppointmentCenterUserDTO>{
        var retVal: ArrayList<AppointmentCenterUserDTO> = ArrayList()
        val apps = appointmentRepository.findAll()
        for(app in apps){
            if(app.donor!=null){
                if(app.donor!!.email.equals(query.email) && Date(app.time.time+1000*60*60*24*60)>query.date){
                    retVal.add(AppointmentCenterUserDTO("NOTAVA","NOTAVA","NOTAVA","NOTAVA",-1,-1.0))
                    return retVal
                }
            }
        }
        val df: DateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm")
        val centers = centerRepository.findAll()
        for(center in centers){
            val end = Date(query.date.time+1000*60*60)
            val appointment = findAppointmentsWithoutUser(query.date,end,center.name)
            if(appointment!=null){
                if(appointment.id==-1){
                    retVal.add(AppointmentCenterUserDTO(center.name,center.address,df.format(query.date),df.format(end),-1,center.rating))
                }else{
                    retVal.add(AppointmentCenterUserDTO(center.name,center.address,df.format(appointment.time),df.format(Date(appointment.time.time+1000*60*60)),
                        appointment.id!!, center.rating
                    ))
                }
            }
        }
        return retVal
    }

    fun findAppointmentsWithoutUser(start: Date, end: Date, centerName: String): Appointment?{
        val allAppointments = appointmentRepository.findAll()
        for(scheduled in allAppointments){
            val endTime = Date(scheduled.time.time+1000*60*60-1)
            if(scheduled.center?.name.equals(centerName)){
//                println("FIRST " + start.toString() + " < " + scheduled.time.toString() + "<" + end.toString() + " is " + (scheduled.time > start && scheduled.time < end))
//                println("SECOND " + start.toString() + " < " + endTime.toString() + "<" + end.toString() + " is " + (endTime > start && endTime < end))
                if(((scheduled.time >= start && scheduled.time < end) || (endTime > start && endTime < end))) {
//                    println("DONOR ID:" + (scheduled.donor?.id ?: "NONE"))
                    if (scheduled.donor == null) {
                        return scheduled;//APPOINTMENT SCHEDULED AND DOES NOT HAVE USER
                    }
                    else{
                        return null;//APPOINTMENT SCHEDULED AND HAS USER
                    }
                }
            }
        }
        return Appointment(-1, time = Date(),null,null,null);//APPOINTMENT DOES NOT EXIST
    }

    fun enrollAppointment(enroll: AppointmentEnrollDTO) : Appointment{
        try{
            val user = userRepository.findOneByEmail(enroll.email)
            val df: DateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm")
//            println("APP ID:" + enroll.appId)
            val appId = Integer.parseInt(enroll.appId)
            if (appId != -1) {
                var appointment = appointmentRepository.findById(appId).get()
                appointment.donor = user
                emailService.sendEmailEnrollQR(
                    "Reservation",
                    "Successful reservation, " + user.name + " " + user.name + " at " + enroll.center + " " + enroll.date,
                    user.email
                )
                return appointmentRepository.save(appointment)
            }
            emailService.sendEmailEnrollQR(
                "Reservation",
                "Successful reservation, " + user.name + " " + user.name + " at " + enroll.center + " " + enroll.date,
                user.email
            )
            return appointmentRepository.save(
                Appointment(
                    null,
                    df.parse(enroll.date),
                    user,
                    centerRepository.findByName(enroll.center).get(),
                    null
                )
            )
        }
        catch(e: Exception){
            return Appointment(-1,Date(),null,null,null);
        }
    }
}