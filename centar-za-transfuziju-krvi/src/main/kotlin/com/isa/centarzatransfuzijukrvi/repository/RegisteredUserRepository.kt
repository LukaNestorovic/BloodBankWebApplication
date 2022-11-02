package com.isa.centarzatransfuzijukrvi.repository

import com.isa.centarzatransfuzijukrvi.model.RegisteredUser
import org.springframework.data.jpa.repository.JpaRepository

interface RegisteredUserRepository : JpaRepository<RegisteredUser, Int> {
}