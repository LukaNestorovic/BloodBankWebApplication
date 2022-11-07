package com.isa.centarzatransfuzijukrvi.controller

import com.isa.centarzatransfuzijukrvi.model.RegisteredUser
import com.isa.centarzatransfuzijukrvi.model.dto.RegisteredUserProfileUpdateDto
import com.isa.centarzatransfuzijukrvi.service.RegisteredUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(path = ["api"])
@CrossOrigin(origins=["*"])
class RegisteredUserController(@Autowired val registeredUserService: RegisteredUserService) {


    @PostMapping(path = ["/register"], consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun createRegisteredUser(@RequestBody registeredUser: RegisteredUser) : ResponseEntity<RegisteredUser>{
        val newRegisteredUser: RegisteredUser = registeredUserService.create(registeredUser)
        return ResponseEntity(newRegisteredUser, HttpStatus.CREATED)
    }
    @PutMapping(path = ["/update"], consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun updateRegisteredUser(@RequestBody newUserInfo: RegisteredUserProfileUpdateDto) : ResponseEntity<RegisteredUser> =
        ResponseEntity(registeredUserService.update(newUserInfo),HttpStatus.ACCEPTED)

}