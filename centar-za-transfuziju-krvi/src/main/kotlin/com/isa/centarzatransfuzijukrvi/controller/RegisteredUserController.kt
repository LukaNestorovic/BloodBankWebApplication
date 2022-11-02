package com.isa.centarzatransfuzijukrvi.controller

import com.isa.centarzatransfuzijukrvi.model.RegisteredUser
import com.isa.centarzatransfuzijukrvi.model.dto.RegisteredUserDTO
import com.isa.centarzatransfuzijukrvi.service.RegisteredUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/api/registration"])
class RegisteredUserController(val registeredUserService: RegisteredUserService) {

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun createRegisteredUser(registeredUserDTO: RegisteredUserDTO) : ResponseEntity<RegisteredUserDTO>{
        val registeredUser: RegisteredUser = RegisteredUser(registeredUserDTO.id, registeredUserDTO.name, registeredUserDTO.surname, registeredUserDTO.email,
            registeredUserDTO.password, registeredUserDTO.adress, registeredUserDTO.city, registeredUserDTO.country, registeredUserDTO.phone, registeredUserDTO.jmbg,
            registeredUserDTO.gender, registeredUserDTO.occupation, registeredUserDTO.information)

        val newRegisteredUser: RegisteredUser = registeredUserService.create(registeredUser)

        val newRegisteredUserDTO: RegisteredUserDTO = RegisteredUserDTO(newRegisteredUser.id, newRegisteredUser.name, newRegisteredUser.surname, newRegisteredUser.email,
            newRegisteredUser.password, newRegisteredUser.adress, newRegisteredUser.city, newRegisteredUser.country, newRegisteredUser.phone, newRegisteredUser.jmbg,
            newRegisteredUser.gender, newRegisteredUser.occupation, newRegisteredUser.information)

        return ResponseEntity(newRegisteredUserDTO, HttpStatus.CREATED)
    }
}