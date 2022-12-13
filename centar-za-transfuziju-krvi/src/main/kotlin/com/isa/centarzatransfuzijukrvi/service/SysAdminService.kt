package com.isa.centarzatransfuzijukrvi.service

import com.isa.centarzatransfuzijukrvi.model.SysAdmin
import com.isa.centarzatransfuzijukrvi.repository.SysAdminRepository
import org.springframework.stereotype.Service

@Service
class SysAdminService(val sysAdminRepository: SysAdminRepository) {
    fun findAll(): List<SysAdmin> = sysAdminRepository.findAll()
}