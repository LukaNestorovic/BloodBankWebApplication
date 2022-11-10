package com.isa.centarzatransfuzijukrvi.repository

import com.isa.centarzatransfuzijukrvi.model.Center
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CenterRepository : JpaRepository<Center,Int> {
}
