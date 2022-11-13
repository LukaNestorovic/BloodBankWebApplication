package com.isa.centarzatransfuzijukrvi.repository

import com.isa.centarzatransfuzijukrvi.model.RegisteredUser
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RegisteredUserRepository : JpaRepository<RegisteredUser, Int> {
    fun findOneByEmail(email: String) : RegisteredUser
}