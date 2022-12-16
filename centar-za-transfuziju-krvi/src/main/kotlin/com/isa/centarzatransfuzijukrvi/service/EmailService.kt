package com.isa.centarzatransfuzijukrvi.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.scheduling.annotation.Async



@Service
class EmailService(
    @Autowired private val mailSender: JavaMailSender
) {

    @Async
    fun sendEmail(subject: String, body: String, targetEmail: String){
        val message = SimpleMailMessage()

        message.setSubject(subject)
        message.setText(body)
        message.setTo(targetEmail)

        mailSender.send(message)
    }


}