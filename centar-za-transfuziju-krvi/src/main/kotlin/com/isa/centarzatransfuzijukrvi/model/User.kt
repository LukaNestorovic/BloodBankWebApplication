package com.isa.centarzatransfuzijukrvi.model

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*


@Entity
@Table(name = "USERS")
class User : UserDetails {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column
    private var username: String? = null

    @JsonIgnore
    @Column
    private var password: String? = null


    @Column
    private var enabled = false


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_role",
        joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "role_id", referencedColumnName = "id")]
    )
    private var roles: List<Role?>? = null
    override fun getUsername(): String {
        return username!!
    }

    fun setUsername(username: String?) {
        this.username = username
    }

    override fun getPassword(): String {
        return password!!
    }

    fun setPassword(password: String?) {
        this.password = password
    }

    fun setRoles(roles: List<Role?>?) {
        this.roles = roles
    }

    fun getRoles(): List<Role?>? {
        return roles
    }

    @JsonIgnore
    override fun getAuthorities(): Collection<GrantedAuthority?> {
        return roles!!
    }

    override fun isEnabled(): Boolean {
        return enabled
    }

    fun setEnabled(enabled: Boolean) {
        this.enabled = enabled
    }

    @JsonIgnore
    override fun isAccountNonExpired(): Boolean {
        return true
    }

    @JsonIgnore
    override fun isAccountNonLocked(): Boolean {
        return true
    }

    @JsonIgnore
    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}