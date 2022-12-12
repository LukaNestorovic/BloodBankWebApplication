package com.isa.centarzatransfuzijukrvi.service

import com.isa.centarzatransfuzijukrvi.model.DonorForm
import com.isa.centarzatransfuzijukrvi.repository.DonorFormRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DonorFormService(@Autowired val donorFormRepository: DonorFormRepository) {
    fun create(donorForm: DonorForm): DonorForm = donorFormRepository.save(donorForm)
}