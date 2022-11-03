package com.isa.centarzatransfuzijukrvi.service

import com.isa.centarzatransfuzijukrvi.model.Staff
import com.isa.centarzatransfuzijukrvi.repository.StaffRepository
import org.springframework.stereotype.Service

@Service
class StaffService(val staffRepository: StaffRepository) {
    fun create(staff: Staff): Staff= staffRepository.save(staff)
    fun read(id: Int): Staff = staffRepository.findById(id).get()
}