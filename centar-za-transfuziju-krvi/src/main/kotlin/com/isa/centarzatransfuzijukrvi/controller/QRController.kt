package com.isa.centarzatransfuzijukrvi.controller

import com.google.zxing.WriterException
import com.isa.centarzatransfuzijukrvi.model.QRCodeGenerator
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import java.io.IOException
import java.util.*


@RestController
@RequestMapping(path = ["api"])
@CrossOrigin(origins=["*"])
class QRController(@Autowired var qrCodeGenerator: QRCodeGenerator) {
    private val QR_CODE_IMAGE_PATH = "C:\\Users\\Ryzen\\Desktop\\FAX\\ISA\\ISA-Projekat\\centar-za-transfuziju-krvi\\src\\main\\resources\\static\\QRCode.png"

    @GetMapping("/qr/{center}/{doctor}/{date}")
    fun getQRCode(model: Model, @PathVariable("center") center: String, @PathVariable("doctor") doctor: String, @PathVariable("date") date: String): String? {
        val medium = "https://rahul26021999.medium.com/"
        val github = "https://github.com/rahul26021999"
        val text = center + " " + doctor + " " + date
        var image: ByteArray? = ByteArray(0)
        try {

            // Generate and Return Qr Code in Byte Array
            image = qrCodeGenerator.getQRCodeImage(medium, 250, 250)

            // Generate and Save Qr Code Image in static/image folder
            qrCodeGenerator.generateQRCodeImage(text, 250, 250, QR_CODE_IMAGE_PATH)
        } catch (e: WriterException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        // Convert Byte Array into Base64 Encode String
        val qrcode: String = Base64.getEncoder().encodeToString(image)
        model.addAttribute("medium", medium)
        model.addAttribute("github", github)
        model.addAttribute("qrcode", qrcode)
        return "qrcode"
    }
}