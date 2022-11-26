package com.isa.centarzatransfuzijukrvi.model

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.security.core.GrantedAuthority
import javax.persistence.*


@Entity
@Table(name = "ROLE")
class Role : GrantedAuthority {
    @get:JsonIgnore
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

    @Column(name = "name")
    var name: String? = null

    @JsonIgnore
    override fun getAuthority(): String {
        return name!!
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}