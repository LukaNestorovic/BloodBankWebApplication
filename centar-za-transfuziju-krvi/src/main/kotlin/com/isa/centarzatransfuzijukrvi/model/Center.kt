package com.isa.centarzatransfuzijukrvi.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Center(
    @Id
    @GeneratedValue
    @Column
    val id: Int?=null,
    @Column
    val name: String,
    @Column
    val address: String,
    @Column
    val description: String,
    @Column
    val rating: Double) {


}