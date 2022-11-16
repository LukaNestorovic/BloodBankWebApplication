package com.isa.centarzatransfuzijukrvi.model.dto

import java.util.*
import javax.persistence.Column

data class DonorFormDTO(
    var id: Int,
    val date: Date?,
    val numberDonation: Int,
    val question1: String,
    val question2: String,
    val question3: String,
    val question4: String,
    val question5: String,
    val question6: String,
    val question7: String,
    val question8: String,
    val question9: String,
    val question10: String,
    val question11: String,
    val question12: String,
    val question13: String,
    val question14: String,
    val userEmail: String
) {
}