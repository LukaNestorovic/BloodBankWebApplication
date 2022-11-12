package com.isa.centarzatransfuzijukrvi.controller

import com.isa.centarzatransfuzijukrvi.model.Center
import com.isa.centarzatransfuzijukrvi.service.CenterService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping(path = ["api"])
@CrossOrigin(origins=["*"])
class CenterController(@Autowired val centerService: CenterService) {

    @PostMapping(path = ["/center"], consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun createCenter(@RequestBody center: Center) : ResponseEntity<Center> {
        val newCenter: Center = centerService.create(center)
        return ResponseEntity(newCenter, HttpStatus.CREATED)
    }

    @GetMapping(path = ["/centers"],produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getCenters() : ResponseEntity<List<Center?>> {
        println("RADI")
        val centerList : List<Center?>? = centerService.findAll()
        println(centerList)
        return ResponseEntity(centerList, HttpStatus.OK)
    }

    @GetMapping(path = ["/centers/{centerId}"])
    fun getCenter(@PathVariable("centerId") centerId: Int) : ResponseEntity<Optional<Center>> {
        val center: Optional<Center> = centerService.findCenterById(centerId)
        return ResponseEntity(center, HttpStatus.OK)
    }
    
    @GetMapping(path = ["/global-centers"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findAllGlobal(): ResponseEntity<List<Center>> = ResponseEntity(centerService.findAll(),HttpStatus.OK)

    @PostMapping(path = ["/global-centers"], consumes = [MediaType.APPLICATION_JSON_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun findFilterGlobal(@RequestBody query: Center) : ResponseEntity<List<Center>> = ResponseEntity(centerService.findFiltered(query),HttpStatus.OK)

}