package com.isa.centarzatransfuzijukrvi.repository

import com.isa.centarzatransfuzijukrvi.model.Role
import org.springframework.data.jpa.repository.JpaRepository

interface RoleRepository : JpaRepository<Role, Int> {
    fun findByName(name: String?): List<Role?>?
}