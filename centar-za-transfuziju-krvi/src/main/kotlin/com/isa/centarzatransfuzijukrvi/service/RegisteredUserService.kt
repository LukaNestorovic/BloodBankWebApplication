package com.isa.centarzatransfuzijukrvi.service

import com.isa.centarzatransfuzijukrvi.model.Loyalty
import com.isa.centarzatransfuzijukrvi.model.RegisteredUser
import com.isa.centarzatransfuzijukrvi.model.dto.RegisteredUserProfileUpdateDto
import com.isa.centarzatransfuzijukrvi.repository.LoyaltyRepository
import com.isa.centarzatransfuzijukrvi.repository.RegisteredUserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RegisteredUserService(@Autowired val registeredUserRepository: RegisteredUserRepository,
                            @Autowired val loyaltyRepository: LoyaltyRepository) {

    fun findOne(id: Int): RegisteredUser {
        return registeredUserRepository.getOne(id)
    }

    fun create(registeredUser: RegisteredUser): RegisteredUser {
        registeredUser.card = Loyalty(user = registeredUser)
        return registeredUserRepository.save(registeredUser)
    }

    fun update(newUserInfo: RegisteredUserProfileUpdateDto) : RegisteredUser{
        var user = registeredUserRepository.findOneByEmail(newUserInfo.email)
        user.updateUserFields(newUserInfo)
        return registeredUserRepository.save(user)
    }
}