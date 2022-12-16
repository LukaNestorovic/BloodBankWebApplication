package com.isa.centarzatransfuzijukrvi.service

import org.springframework.stereotype.Service
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper


@Service
class EmailService(
    private val mailSender: JavaMailSender
) {
    fun sendEmail(subject: String, body: String, targetEmail: String) : String{
        val message = SimpleMailMessage()

        message.setSubject(subject)
        message.setText(body)
        message.setTo(targetEmail)

        mailSender.send(message)
        return ("Mail sent")
    }


}