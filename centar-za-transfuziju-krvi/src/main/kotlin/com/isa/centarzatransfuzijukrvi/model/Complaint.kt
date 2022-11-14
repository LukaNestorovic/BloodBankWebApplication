package com.isa.centarzatransfuzijukrvi.model

import com.fasterxml.jackson.annotation.JsonIgnore
import java.util.Date
import javax.persistence.*
import javax.persistence.CascadeType.*

@Entity
data class Complaint(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    val id: Int?=null,
    @Column(nullable = false)
    val text: String,
    @Column(nullable = false)
    val date: Date,
    @JsonIgnore
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    val user: RegisteredUser,
    @JsonIgnore
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "center_id", referencedColumnName = "id")
    val center: Center,
    @JsonIgnore
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "staff_id", referencedColumnName = "id", nullable = true)
    val staff: Staff,
) {
}