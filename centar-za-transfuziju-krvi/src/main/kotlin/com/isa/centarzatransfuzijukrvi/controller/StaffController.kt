package com.isa.centarzatransfuzijukrvi.controller

import com.isa.centarzatransfuzijukrvi.model.Staff
import com.isa.centarzatransfuzijukrvi.service.RegisteredUserService
import com.isa.centarzatransfuzijukrvi.service.StaffService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class StaffController(val staffService: StaffService) {
    //TRIED OUT CRUD NOT THE FINAL METHODS
    @PostMapping(path = ["/registration/staff"],
                consumes = [MediaType.APPLICATION_JSON_VALUE],
                produces = [MediaType.APPLICATION_JSON_VALUE])
    fun create(@RequestBody staff : Staff): ResponseEntity<Staff> =
        ResponseEntity(staffService.create(staff),HttpStatus.CREATED)
    //TRIED OUT CRUD NOT THE FINAL METHODS
    @GetMapping(path = ["/registration/staff/{id}"],
                produces = [MediaType.APPLICATION_JSON_VALUE])
    fun read(@PathVariable id: Int): ResponseEntity<Staff> =
        ResponseEntity(staffService.read(id),HttpStatus.FOUND)
    //TRIED OUT CRUD NOT THE FINAL METHODS
    @PutMapping(path = ["/registration/staff/{id}"],
                consumes = [MediaType.APPLICATION_JSON_VALUE],
                produces = [MediaType.APPLICATION_JSON_VALUE])
    fun update(@PathVariable id: Int, @RequestBody staff : Staff) : ResponseEntity<Staff> =
        ResponseEntity(staffService.update(id,staff),HttpStatus.ACCEPTED)
}