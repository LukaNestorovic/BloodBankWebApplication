package com.isa.centarzatransfuzijukrvi.model.dto

import javax.persistence.Column

data class RegisteredUserProfileUpdateDto(
    val email: String,
    val name: String?,
    val surname: String?,
    val password: String?,
    val address: String?,
    val city: String?,
    val country: String?,
    val phone: String?,
    val gender: String?,
    val occupation: String?,
    val information: String?,
) {
}