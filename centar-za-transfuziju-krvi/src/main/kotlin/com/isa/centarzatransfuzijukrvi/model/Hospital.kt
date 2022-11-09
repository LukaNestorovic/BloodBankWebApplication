package com.isa.centarzatransfuzijukrvi.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Hospital(
    @Id
    @GeneratedValue
    @Column
    val id: Int?,
    @Column
    val name: String?,
    @Column
    val address: String?,
    @Column
    val description: String?
) {
    constructor(): this(null,null,null,null)
}