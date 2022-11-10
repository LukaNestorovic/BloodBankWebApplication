package com.isa.centarzatransfuzijukrvi.model

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
data class Loyalty(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    val id: Int,
    @Column
    val points: Int = 0,
    @Column
    val category: String = "Regular",
    @JsonIgnore
    @OneToOne(mappedBy = "card")
    var user: RegisteredUser?=null,
)