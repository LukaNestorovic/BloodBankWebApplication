package com.isa.centarzatransfuzijukrvi.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class SysAdmin(
    @Id
    @GeneratedValue
    @Column
    val id: Int?=null,
    @Column
    val name: String,
    @Column
    val surname: String,
    @Column
    val email: String,
    @Column
    val password: String,
    @Column
    val address: String,
    @Column
    val city: String,
    @Column
    val country: String,
    @Column
    val phone: String,
    @Column
    val jmbg: String,
    @Column
    val gender: String,
    @Column
    val occupation: String,
    @Column
    val information: String,
    @Column
    val role: String
) {
}