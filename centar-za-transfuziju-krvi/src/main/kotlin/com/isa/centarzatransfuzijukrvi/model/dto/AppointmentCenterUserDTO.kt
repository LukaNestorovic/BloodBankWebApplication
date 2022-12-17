package com.isa.centarzatransfuzijukrvi.model.dto

import java.util.*


data class AppointmentCenterUserDTO( // BACK => FRONT USER APPOINTMENT QUERY
    val name: String,
    val address: String,
    val start: String,
    val end: String,
    val appId: Int,
    val rating: Double
)
