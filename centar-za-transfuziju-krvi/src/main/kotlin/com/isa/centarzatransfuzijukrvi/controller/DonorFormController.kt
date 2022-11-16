package com.isa.centarzatransfuzijukrvi.controller

import com.isa.centarzatransfuzijukrvi.model.DonorForm
import com.isa.centarzatransfuzijukrvi.model.dto.DonorFormDTO
import com.isa.centarzatransfuzijukrvi.service.DonorFormService
import com.isa.centarzatransfuzijukrvi.service.RegisteredUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(path = ["api"])
@CrossOrigin(origins=["*"])
class DonorFormController(@Autowired val donorFormService: DonorFormService, @Autowired val registeredUserService: RegisteredUserService) {
    @PostMapping(path = ["/donorform"], consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun createDonorForm(@RequestBody donorFormDTO: DonorFormDTO) : ResponseEntity<DonorForm> {
        val email = donorFormDTO.userEmail
        val user = registeredUserService.findByEmail(email)
        val donorForm = DonorForm(donorFormDTO.id, donorFormDTO.date, donorFormDTO.numberDonation, donorFormDTO.question1, donorFormDTO.question2, donorFormDTO.question3,
            donorFormDTO.question4, donorFormDTO.question5, donorFormDTO.question6, donorFormDTO.question7, donorFormDTO.question8, donorFormDTO.question9,
            donorFormDTO.question10, donorFormDTO.question11, donorFormDTO.question12, donorFormDTO.question13, donorFormDTO.question14, user)
        val newDonorForm: DonorForm = donorFormService.create(donorForm)
        return ResponseEntity(newDonorForm, HttpStatus.CREATED)
    }
}