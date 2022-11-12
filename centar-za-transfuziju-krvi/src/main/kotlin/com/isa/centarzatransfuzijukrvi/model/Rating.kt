package com.isa.centarzatransfuzijukrvi.model

import javax.persistence.*

data class Rating(
    @Id
    @GeneratedValue
    @Column(nullable = false)
    val roomRating: Int,
    @Column(nullable = false)
    val staffRating: Int,
    @Column(nullable = false)
    val hospitalityRating: Int,
    @Column(nullable = false)
    val locationRating: Int,
    @Column(nullable = false)
    val cleanlinessGrade: Int,
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    val user: RegisteredUser,

)
