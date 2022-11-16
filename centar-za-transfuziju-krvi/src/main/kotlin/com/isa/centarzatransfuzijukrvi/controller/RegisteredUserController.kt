package com.isa.centarzatransfuzijukrvi.controller

import com.isa.centarzatransfuzijukrvi.model.RegisteredUser
import com.isa.centarzatransfuzijukrvi.model.dto.RegisteredUserDTO2
import com.isa.centarzatransfuzijukrvi.model.dto.RegisteredUserDto
import com.isa.centarzatransfuzijukrvi.service.RegisteredUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping(path = ["api"])
@CrossOrigin(origins=["*"])
class RegisteredUserController(@Autowired val registeredUserService: RegisteredUserService) {


    @PostMapping(path = ["/register"], consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun createRegisteredUser(@RequestBody registeredUser: RegisteredUser) : ResponseEntity<RegisteredUser>{
        val newRegisteredUser: RegisteredUser = registeredUserService.create(registeredUser)
        return ResponseEntity(newRegisteredUser, HttpStatus.CREATED)
    }
    @PutMapping(path = ["/profile"], consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun updateRegisteredUser(@RequestBody newUserInfo: RegisteredUserDto) : ResponseEntity<RegisteredUser> {
        val retVal = registeredUserService.update(newUserInfo)
        if(retVal==null){
            return ResponseEntity(null,HttpStatus.UNAUTHORIZED)
        }
        return ResponseEntity(retVal,HttpStatus.ACCEPTED)
    }

    @PostMapping(path = ["/profile"], consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findRegisteredUser(@RequestBody userQuery: RegisteredUserDto) : ResponseEntity<RegisteredUserDto> {
        println(userQuery.email)
        val user = registeredUserService.findByEmail(userQuery.email)
        val userDTO = RegisteredUserDto(user)
        return ResponseEntity(userDTO,HttpStatus.ACCEPTED)
    }

    @PostMapping(path = ["/login"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun logIn(@RequestBody registeredUserDTO2: RegisteredUserDTO2) : ResponseEntity<RegisteredUserDTO2>{
        val userList : List<RegisteredUser> = registeredUserService.findAll()
        val registeredUser3: RegisteredUserDTO2 = RegisteredUserDTO2(registeredUserDTO2.id, registeredUserDTO2.email, registeredUserDTO2.password)

        for(registeredUser: RegisteredUser in userList){
            if(registeredUser.email == registeredUser3.email){
                return if(registeredUser.password == registeredUser3.password){
                    registeredUser3.id = registeredUser.id
                    ResponseEntity(registeredUser3, HttpStatus.OK)
                } else ResponseEntity(registeredUser3, HttpStatus.BAD_REQUEST)
            }
        }
        return ResponseEntity(registeredUser3, HttpStatus.NOT_FOUND)
    }

    @GetMapping(path = ["/users/{userId}"])
    fun getRegisteredUser(@PathVariable("userId") userId: Int) : ResponseEntity<Optional<RegisteredUser>> {
        val registeredUser: Optional<RegisteredUser> = registeredUserService.findById(userId)
        return ResponseEntity(registeredUser, HttpStatus.OK)
    }
}