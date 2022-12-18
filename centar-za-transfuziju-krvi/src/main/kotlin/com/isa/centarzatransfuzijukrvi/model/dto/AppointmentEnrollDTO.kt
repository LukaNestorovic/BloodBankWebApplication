package com.isa.centarzatransfuzijukrvi.model.dto

data class AppointmentEnrollDTO(// FRONT => BACK USER ENROLL FOR APPOINTMENT
    val email: String,
    val center: String,
    val date: String,
    val appId: String
)
