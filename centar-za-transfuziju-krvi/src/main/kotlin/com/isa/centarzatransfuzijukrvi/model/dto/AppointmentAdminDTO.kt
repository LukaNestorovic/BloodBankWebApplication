package com.isa.centarzatransfuzijukrvi.model.dto

import java.util.Date

data class AppointmentAdminDTO(// FRONT => BACK SCHEDULING APPOINTMENTS ADMIN
    val date: Date,
    val centerName: String,
    val email: String
)
