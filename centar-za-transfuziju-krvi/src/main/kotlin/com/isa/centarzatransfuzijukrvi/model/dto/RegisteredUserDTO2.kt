package com.isa.centarzatransfuzijukrvi.model.dto

import com.isa.centarzatransfuzijukrvi.model.RegisteredUser

data class RegisteredUserDTO2(
    var id: Int,
    var email: String,
    var password: String,
    var role: String?,
    var enable: Boolean?,
    var penalty: Int
) {
    constructor(user: RegisteredUser) : this(user.id, user.email, user.password, user.role, user.enable, user.penalty)
}