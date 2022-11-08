package com.isa.centarzatransfuzijukrvi.controller

import com.isa.centarzatransfuzijukrvi.model.Center
import com.isa.centarzatransfuzijukrvi.service.CenterService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(path = ["api"])
@CrossOrigin(origins=["*"])
class CenterController(val centerService: CenterService) {

    @PostMapping(path = ["/center"], consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun createReCenter(@RequestBody center: Center) : ResponseEntity<Center> {
        val newCenter: Center = centerService.create(center)
        return ResponseEntity(newCenter, HttpStatus.CREATED)
    }

    @GetMapping(path = ["/centers"],produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getCenter() : ResponseEntity<List<Center?>> {
        println("RADI")
        val centerList : List<Center?>? = centerService.findAll()
        println(centerList)
        return ResponseEntity(centerList, HttpStatus.OK)
    }
}