package com.isa.centarzatransfuzijukrvi.model.dto

data class RegisteredUserDTO(
    val id: Int,
    val name: String,
    val surname: String,
    val email: String,
    val password: String,
    val adress: String,
    val city: String,
    val country: String,
    val phone: String,
    val jmbg: String,
    val gender: String,
    val occupation: String,
    val information: String,
) {
}