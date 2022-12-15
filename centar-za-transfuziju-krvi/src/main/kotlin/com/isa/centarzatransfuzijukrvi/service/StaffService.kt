package com.isa.centarzatransfuzijukrvi.service

import com.isa.centarzatransfuzijukrvi.model.Staff
import com.isa.centarzatransfuzijukrvi.repository.StaffRepository
import org.springframework.stereotype.Service

@Service
class StaffService(val staffRepository: StaffRepository) {
//    fun create(staff: Staff): Staff= staffRepository.save(staff)
//    fun read(id: Int): Staff = staffRepository.findById(id).get()
//    fun update(id: Int, staff: Staff): Staff =
//            staffRepository.save(
//                        Staff(id,
//                              staff.name,
//                              staff.surname,
//                              staff.gender,
//                              staff.email,
//                              staff.password,
//                              staff.role,
//                              staff.reports))
    fun findAll(): List<Staff> = staffRepository.findAll()
}