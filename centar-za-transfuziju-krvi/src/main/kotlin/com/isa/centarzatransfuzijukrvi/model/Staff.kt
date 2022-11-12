package com.isa.centarzatransfuzijukrvi.model

import javax.persistence.*

@Entity
data class Staff(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    val id: Int?,
    @Column
    val name: String?,
    @Column
    val surname: String?,
    @Column
    val gender: String?,
    @Column
    val email: String?,
    @Column
    val password: String?,
    @Column
    val role: String?
) {
    constructor(): this(null,null,null,null,null,null,null)
}