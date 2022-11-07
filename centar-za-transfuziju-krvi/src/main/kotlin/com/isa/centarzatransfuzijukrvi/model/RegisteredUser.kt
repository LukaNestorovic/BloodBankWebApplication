package com.isa.centarzatransfuzijukrvi.model

import javax.persistence.*

@Entity
data class RegisteredUser(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    val id: Int?,
    @Column
    val name: String?,
    @Column
    val surname: String?,
    @Column
    val email: String?,
    @Column
    val password: String?,
    @Column
    val address: String?,
    @Column
    val city: String?,
    @Column
    val country: String?,
    @Column
    val phone: String?,
    @Column
    val jmbg: String?,
    @Column
    val gender: String?,
    @Column
    val occupation: String?,
    @Column
    val information: String?,
    @OneToOne(cascade = [CascadeType.ALL],orphanRemoval = true)
    @JoinColumn(name = "card", referencedColumnName = "id")
    var card: Loyalty?,
) {
    constructor() : this(null,null,null,null,null,null,null,null,null,null,null,null,null, null)
}