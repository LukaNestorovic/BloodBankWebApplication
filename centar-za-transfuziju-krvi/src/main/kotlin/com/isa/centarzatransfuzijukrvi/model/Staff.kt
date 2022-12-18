package com.isa.centarzatransfuzijukrvi.model

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
data class Staff(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    val id: Int?,
    @Column(nullable = false)
    val name: String,
    @Column(nullable = false)
    val surname: String,
    @Column(nullable = false)
    val gender: String,
    @Column(nullable = false,unique = true)
    val email: String,
    @Column(nullable = false)
    val password: String,
    @Column(nullable = false)
    val role: String,
    @Column(nullable = false)
    val address: String,
    @Column(nullable = false)
    val city: String,
    @Column(nullable = false)
    val country: String,
    @Column(nullable = false)
    val phone: String,
    @Column(nullable = false)
    val jmbg: String,
    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    val reports: List<ExamReport>?,
    @OneToMany(mappedBy = "staff", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    val complaints: List<Complaint>?,
    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    val appointments: List<Appointment>?,
    @JsonIgnore
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="center_id", referencedColumnName = "id")
    val center: Center
)