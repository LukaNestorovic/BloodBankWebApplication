package com.isa.centarzatransfuzijukrvi.model

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
    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    val reports: List<ExamReport>,
)