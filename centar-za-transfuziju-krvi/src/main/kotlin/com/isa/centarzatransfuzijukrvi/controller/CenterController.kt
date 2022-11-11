package com.isa.centarzatransfuzijukrvi.controller

import com.isa.centarzatransfuzijukrvi.model.Center
import com.isa.centarzatransfuzijukrvi.service.CenterService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(path = ["api"])
@CrossOrigin(origins=["*"])
class CenterController(@Autowired val centerService: CenterService) {

    @GetMapping(path = ["/global-centers"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findAllGlobal(): ResponseEntity<List<Center>> = ResponseEntity(centerService.findAll(),HttpStatus.OK)

    @PostMapping(path = ["/global-centers"], consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findFilterGlobal(@RequestBody query: Center) : ResponseEntity<List<Center>> = ResponseEntity(centerService.findFiltered(query),HttpStatus.OK)

}