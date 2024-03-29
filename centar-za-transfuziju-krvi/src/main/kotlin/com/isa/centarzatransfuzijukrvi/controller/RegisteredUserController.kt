package com.isa.centarzatransfuzijukrvi.controller

import com.isa.centarzatransfuzijukrvi.model.RegisteredUser
import com.isa.centarzatransfuzijukrvi.model.Staff
import com.isa.centarzatransfuzijukrvi.model.SysAdmin
import com.isa.centarzatransfuzijukrvi.model.dto.DeleteDTO
import com.isa.centarzatransfuzijukrvi.model.dto.RegisteredUserDTO2
import com.isa.centarzatransfuzijukrvi.model.dto.RegisteredUserDto
import com.isa.centarzatransfuzijukrvi.service.RegisteredUserService
import com.isa.centarzatransfuzijukrvi.service.StaffService
import com.isa.centarzatransfuzijukrvi.service.SysAdminService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*


@RestController
@RequestMapping(path = ["api"])
@CrossOrigin(origins=["*"])
class RegisteredUserController(@Autowired val registeredUserService: RegisteredUserService, @Autowired val staffService: StaffService, @Autowired val sysAdminService: SysAdminService) {


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
        val registeredUser3 : RegisteredUserDTO2 = RegisteredUserDTO2(registeredUserDTO2.id, registeredUserDTO2.email, registeredUserDTO2.password, registeredUserDTO2.role, registeredUserDTO2.enable, registeredUserDTO2.penalty)
        val staffList : List<Staff> =  staffService.findAll()
        val sysAdminList : List<SysAdmin> = sysAdminService.findAll()

        for(registeredUser: RegisteredUser in userList){
            if(registeredUser.email == registeredUser3.email){
                return if(registeredUser.password == registeredUser3.password){
                    registeredUser3.id = registeredUser.id
                    registeredUser3.role = registeredUser.role
                    registeredUser3.enable = registeredUser.enable
                    registeredUser3.penalty = registeredUser.penalty
                    ResponseEntity(registeredUser3, HttpStatus.OK)
                } else ResponseEntity(registeredUser3, HttpStatus.BAD_REQUEST)
            }
        }

        for(staff: Staff in staffList){
            if(staff.email == registeredUser3.email){
                return if(staff.password == registeredUser3.password){
                    registeredUser3.id = staff.id!!
                    registeredUser3.role = staff.role
                    registeredUser3.enable = null
                    registeredUser3.penalty = 0
                    ResponseEntity(registeredUser3, HttpStatus.OK)
                } else ResponseEntity(registeredUser3, HttpStatus.BAD_REQUEST)
            }
        }

        for(sysAdmin: SysAdmin in sysAdminList){
            if(sysAdmin.email == registeredUser3.email){
                return if(sysAdmin.password == registeredUser3.password){
                    registeredUser3.id = sysAdmin.id!!
                    registeredUser3.role = sysAdmin.role
                    registeredUser3.enable = null
                    registeredUser3.penalty = 0
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

    @PutMapping(path = ["/enable"], consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun setEnable(@RequestBody dto: DeleteDTO) : ResponseEntity<RegisteredUser> {
        var povratna = registeredUserService.setEnable(dto.email)
        return ResponseEntity(povratna, HttpStatus.OK)
    }
}