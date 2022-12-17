package com.isa.centarzatransfuzijukrvi.model

import javax.persistence.*

@Entity
data class Center(
    @Id
    @GeneratedValue
    @Column
    val id: Int?=null,
    @Column
    val name: String,
    @Column
    val address: String,
    @Column
    val description: String,
    @Column
    val rating: Double,
    @OneToMany(mappedBy = "center", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    val complaints: List<Complaint>?,
    @OneToMany(mappedBy = "center", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    val ratings: List<Rating>?,
    @OneToMany(mappedBy = "center", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    val appointments: List<Appointment>?,
    @OneToMany(mappedBy = "center", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    val staff: List<Staff>?,
) {


}