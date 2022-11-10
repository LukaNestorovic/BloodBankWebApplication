package com.isa.centarzatransfuzijukrvi.service

import com.isa.centarzatransfuzijukrvi.model.Center
import com.isa.centarzatransfuzijukrvi.repository.CenterRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class CenterService(@Autowired val centerRepository: CenterRepository) {

    fun create(center: Center): Center {
        return centerRepository.save(center)
    }

    fun findAll(): List<Center?>? {
        return centerRepository.findAll()
    }

    fun findCenterById(id: Int): Optional<Center> {
        return centerRepository.findById(id)
    }
}