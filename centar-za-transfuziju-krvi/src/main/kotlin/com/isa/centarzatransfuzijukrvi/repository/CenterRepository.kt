package com.isa.centarzatransfuzijukrvi.repository

import com.isa.centarzatransfuzijukrvi.model.Center
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface CenterRepository : JpaRepository<Center,Int> {
    @Query("SELECT c FROM Center c WHERE (:name is null or :name='' or c.name like %:name%) " +
                                    "and (:description is null or :description='' or c.description like %:description%) " +
                                    "and (:address is null or :address='' or c.address like %:address%) " +
                                    "and (:rating is null or c.rating >= :rating)")
    fun findCentersByFilter(@Param("name") name: String,@Param("description") description: String, @Param("address") address: String,@Param("rating") rating: Double): List<Center> ;

    fun findByName(name:String): Optional<Center>
}
