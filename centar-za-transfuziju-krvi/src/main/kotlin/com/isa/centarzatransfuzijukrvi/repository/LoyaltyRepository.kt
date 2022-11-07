package com.isa.centarzatransfuzijukrvi.repository

import com.isa.centarzatransfuzijukrvi.model.Loyalty
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LoyaltyRepository : JpaRepository<Loyalty,Int>{
}