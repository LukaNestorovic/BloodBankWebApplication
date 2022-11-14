package com.isa.centarzatransfuzijukrvi.model

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*
@Entity
data class Rating(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    val id: Int?=null,
    @Column(nullable = false)
    val roomRating: Int,
    @Column(nullable = false)
    val staffRating: Int,
    @Column(nullable = false)
    val hospitalityRating: Int,
    @Column(nullable = false)
    val locationRating: Int,
    @Column(nullable = false)
    val cleanlinessRating: Int,
    @JsonIgnore
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    val user: RegisteredUser,
    @JsonIgnore
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "center_id", referencedColumnName = "id")
    val center: Center,
)
