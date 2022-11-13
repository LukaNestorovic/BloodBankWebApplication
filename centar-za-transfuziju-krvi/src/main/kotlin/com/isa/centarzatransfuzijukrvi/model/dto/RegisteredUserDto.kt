package com.isa.centarzatransfuzijukrvi.model.dto

import com.isa.centarzatransfuzijukrvi.model.Loyalty
import com.isa.centarzatransfuzijukrvi.model.RegisteredUser

data class RegisteredUserDto(
    val email: String,
    val jmbg: String,
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
    var card: Loyalty?,
) {
    constructor(user : RegisteredUser) :
    this(
        user.email.toString(),
        user.jmbg.toString(),
        user.name,
        user.surname,
        user.password,
        user.address,
        user.city,
        user.country,
        user.phone,
        user.gender,
        user.occupation,
        user.information,
        user.card)
}