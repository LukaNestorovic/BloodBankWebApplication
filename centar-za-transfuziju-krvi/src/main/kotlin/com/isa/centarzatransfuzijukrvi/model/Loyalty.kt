package com.isa.centarzatransfuzijukrvi.model

import javax.persistence.*

@Entity
data class Loyalty(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    val id: Int?=null,
    @Column
    val points: Int = 0,
    @Column
    val category: String = "Regular",
    @OneToOne(mappedBy = "card")
    val user: RegisteredUser?=null,
)