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
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    val complaints: List<Complaint>
) {


}