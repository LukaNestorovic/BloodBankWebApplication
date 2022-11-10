package com.isa.centarzatransfuzijukrvi.controller

import com.isa.centarzatransfuzijukrvi.model.Center
import com.isa.centarzatransfuzijukrvi.service.CenterService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["api"])
@CrossOrigin(origins=["*"])
class CenterController(@Autowired val centerService: CenterService) {

    @GetMapping(path = ["/centers"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findAll(): ResponseEntity<List<Center>> = ResponseEntity(centerService.findAll(),HttpStatus.OK)

}