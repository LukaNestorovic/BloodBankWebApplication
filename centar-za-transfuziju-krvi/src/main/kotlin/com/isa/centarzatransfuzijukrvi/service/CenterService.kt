package com.isa.centarzatransfuzijukrvi.service

import com.isa.centarzatransfuzijukrvi.model.Center
import com.isa.centarzatransfuzijukrvi.repository.CenterRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CenterService(@Autowired val centerRepository: CenterRepository) {

    fun findAll(): List<Center> = centerRepository.findAll()

}