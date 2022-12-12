package com.isa.centarzatransfuzijukrvi.model

import java.util.*
import javax.persistence.*

@Entity
data class DonorForm(
    @Id
    @GeneratedValue
    @Column
    val id: Int?=null,
    @Column
    val date: Date?,
    @Column
    val numberDonation: Int,
    @Column
    val question1: String,
    @Column
    val question2: String,
    @Column
    val question3: String,
    @Column
    val question4: String,
    @Column
    val question5: String,
    @Column
    val question6: String,
    @Column
    val question7: String,
    @Column
    val question8: String,
    @Column
    val question9: String,
    @Column
    val question10: String,
    @Column
    val question11: String,
    @Column
    val question12: String,
    @Column
    val question13: String,
    @Column
    val question14: String,
    @OneToOne(cascade = [CascadeType.ALL],orphanRemoval = true)
    @JoinColumn(name = "user", referencedColumnName = "id")
    val user: RegisteredUser
) {
}
