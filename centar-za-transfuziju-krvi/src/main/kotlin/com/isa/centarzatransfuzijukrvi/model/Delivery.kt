package com.isa.centarzatransfuzijukrvi.model

import java.util.Date
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Delivery(
    @Id
    @GeneratedValue
    @Column
    val id: Int?,
    @Column
    val bloodType: String?,
    @Column
    val amount: Double?,
    @Column
    val date: Date?
) {
    constructor(): this(null,null,null,null)
}