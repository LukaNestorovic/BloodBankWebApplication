package com.isa.centarzatransfuzijukrvi.model

import java.util.*
import javax.persistence.*

@Entity
data class Appointment(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    val id: Int?=null,
    @Column(nullable = false)
    val time: Date,
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="user_id", referencedColumnName = "id")
    val donor: RegisteredUser,
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="center_id", referencedColumnName = "id")
    val center: Center,
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="doctor_id", referencedColumnName = "id")
    val doctor: Staff
)
