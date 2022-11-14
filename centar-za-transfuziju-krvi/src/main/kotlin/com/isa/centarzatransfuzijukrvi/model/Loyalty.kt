package com.isa.centarzatransfuzijukrvi.model

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
data class Loyalty(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    val id: Int?=null,
    @Column(nullable = false)
    val points: Int = 0,
    @Column(nullable = false)
    val category: String = "Regular",
    @Column
    val benefits: String = "No benefits",
    @JsonIgnore
    @OneToOne(mappedBy = "card")
    var user: RegisteredUser?,
)