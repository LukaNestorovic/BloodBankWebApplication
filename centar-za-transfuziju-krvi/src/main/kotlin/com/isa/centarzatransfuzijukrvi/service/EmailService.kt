package com.isa.centarzatransfuzijukrvi.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.FileSystemResource
import org.springframework.stereotype.Service
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.scheduling.annotation.Async
import java.io.File


@Service
class EmailService(
    @Autowired private val mailSender: JavaMailSender
) {


    @Async
    fun sendEmail(subject: String, body: String, targetEmail: String):String{
        val message = SimpleMailMessage()

        message.setSubject(subject)
        message.setText(body)
        message.setTo(targetEmail)

        mailSender.send(message)
        return ("Mail sent")
    }

    @Async
    fun sendEmailAtt(subject: String, body: String, targetEmail: String):String{
        val message = SimpleMailMessage()
        val mimeMessage = mailSender.createMimeMessage()
        val mimeMessageHelper = MimeMessageHelper(mimeMessage, true)

        mimeMessageHelper.setSubject(subject)
        mimeMessageHelper.setText(body)
        mimeMessageHelper.setTo(targetEmail)
        val file: FileSystemResource = FileSystemResource(File("C:\\Users\\Ryzen\\Desktop\\FAX\\ISA\\ISA-Projekat\\centar-za-transfuziju-krvi\\src\\main\\resources\\static\\QRCode.png"))
        mimeMessageHelper.addAttachment(file.filename, file)


        mailSender.send(mimeMessage)
        return ("Mail sent")
    }

}