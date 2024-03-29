package com.isa.centarzatransfuzijukrvi.controller

import com.isa.centarzatransfuzijukrvi.model.Email
import com.isa.centarzatransfuzijukrvi.service.EmailService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(path = ["api"])
@CrossOrigin(origins=["*"])
class EmailController(@Autowired val emailService: EmailService) {
    @PostMapping("/sendemail/{email}")
    fun sendMail(@PathVariable("email") email: String): String {
        return emailService.sendEmail("Registracija", "http://localhost:3000/success", email)
    }

    @PostMapping("/sendemailqr/{email}")
    fun sendMailQR(@PathVariable("email") email: String): String {
        println(email)
        return emailService.sendEmailAtt("QR", " ", email)
    }
}