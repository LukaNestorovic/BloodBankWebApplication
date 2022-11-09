package com.isa.centarzatransfuzijukrvi.model

import com.isa.centarzatransfuzijukrvi.model.dto.RegisteredUserDto
import javax.persistence.*

@Entity
data class RegisteredUser(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    val id: Int?,
    @Column
    var name: String?,
    @Column
    var surname: String?,
    @Column
    val email: String?,
    @Column
    var password: String?,
    @Column
    var address: String?,
    @Column
    var city: String?,
    @Column
    var country: String?,
    @Column
    var phone: String?,
    @Column
    val jmbg: String?,
    @Column
    var gender: String?,
    @Column
    var occupation: String?,
    @Column
    var information: String?,
    @OneToOne(cascade = [CascadeType.ALL],orphanRemoval = true)
    @JoinColumn(name = "card", referencedColumnName = "id")
    var card: Loyalty?,
) {
    constructor() : this(null,null,null,null,null,null,null,null,null,null,null,null,null, null)
    fun updateUserFields(newData: RegisteredUserDto){
        if(newData.name!=null) this.name = newData.name
        if(newData.surname!=null) this.surname = newData.surname
        if(newData.address!=null) this.address = newData.address
        if(newData.city!=null) this.city = newData.city
        if(newData.country!=null) this.country = newData.country
        if(newData.phone!=null) this.phone = newData.phone
        if(newData.gender!=null) this.gender = newData.gender
        if(newData.occupation!=null) this.occupation = newData.occupation
        if(newData.information!=null) this.information = newData.information

    }
}