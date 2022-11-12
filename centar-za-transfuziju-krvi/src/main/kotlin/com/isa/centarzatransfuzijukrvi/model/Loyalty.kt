package com.isa.centarzatransfuzijukrvi.model

import com.fasterxml.jackson.annotation.JsonIgnore
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
    @Column
    val benefits: String = "No benefits",
    @JsonIgnore
    @OneToOne(mappedBy = "card")
    var user: RegisteredUser?=null,
)