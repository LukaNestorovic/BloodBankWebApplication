package com.isa.centarzatransfuzijukrvi.service

import com.isa.centarzatransfuzijukrvi.model.Loyalty
import com.isa.centarzatransfuzijukrvi.model.RegisteredUser
import com.isa.centarzatransfuzijukrvi.repository.LoyaltyRepository
import com.isa.centarzatransfuzijukrvi.repository.RegisteredUserRepository
import org.springframework.stereotype.Service

@Service
class RegisteredUserService(val registeredUserRepository: RegisteredUserRepository,
                            val loyaltyRepository: LoyaltyRepository) {

    fun findOne(id: Int): RegisteredUser {
        return registeredUserRepository.getOne(id)
    }

    fun create(registeredUser: RegisteredUser): RegisteredUser {
        registeredUser.card = loyaltyRepository.save(Loyalty())
        return registeredUserRepository.save(registeredUser)
    }
}