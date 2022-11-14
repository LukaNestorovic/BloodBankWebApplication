package com.isa.centarzatransfuzijukrvi.model

import com.isa.centarzatransfuzijukrvi.model.dto.RegisteredUserDto
import javax.persistence.*
import com.fasterxml.jackson.annotation.JsonIgnore

@Entity
data class RegisteredUser(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    val id: Int,
    @Column(nullable = false)
    var name: String,
    @Column(nullable = false)
    var surname: String,
    @Column(nullable = false)
    val email: String,
    @Column(nullable = false)
    var password: String,
    @Column(nullable = false)
    var address: String,
    @Column(nullable = false)
    var city: String,
    @Column(nullable = false)
    var country: String,
    @Column(nullable = false)
    var phone: String,
    @Column(nullable = false)
    val jmbg: String,
    @Column(nullable = false)
    var gender: String,
    @Column(nullable = false)
    var occupation: String,
    @Column(nullable = false)
    var information: String,
    @OneToOne(cascade = [CascadeType.ALL],orphanRemoval = true)
    @JoinColumn(name = "card", referencedColumnName = "id")
    var card: Loyalty?,
    @Column
    val bloodGroup: String?,
    @JsonIgnore
    @OneToOne(mappedBy = "user")
    val donorForm: DonorForm?,
    @OneToMany(mappedBy = "center", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    val complaints: List<Complaint>?,
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    val ratings: List<Complaint>?,
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    val reports: List<ExamReport>?,
) {
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