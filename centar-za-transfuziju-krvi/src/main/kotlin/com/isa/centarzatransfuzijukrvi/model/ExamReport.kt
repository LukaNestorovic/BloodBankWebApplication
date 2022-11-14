package com.isa.centarzatransfuzijukrvi.model

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
data class ExamReport(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    val id: Int?=null,
    @Column(nullable = false)
    val height: Float,
    @Column(nullable = false)
    val weight: Float,
    @Column(nullable = false)
    val hiv: Boolean,
    @Column(nullable = false)
    val hbv: Boolean,
    @Column(nullable = false)
    val hcv: Boolean,
    @Column(nullable = false)
    val o2: Int,
    @JsonIgnore
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    val user: RegisteredUser,
    @JsonIgnore
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "staff_id", referencedColumnName = "id")
    val doctor: Staff,
)
