package com.isa.centarzatransfuzijukrvi.service

import com.isa.centarzatransfuzijukrvi.model.Loyalty
import com.isa.centarzatransfuzijukrvi.model.RegisteredUser
import com.isa.centarzatransfuzijukrvi.model.dto.RegisteredUserDto
import com.isa.centarzatransfuzijukrvi.repository.LoyaltyRepository
import com.isa.centarzatransfuzijukrvi.repository.RegisteredUserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class RegisteredUserService(@Autowired val registeredUserRepository: RegisteredUserRepository,
                            @Autowired val loyaltyRepository: LoyaltyRepository) {

    fun findOne(id: Int): RegisteredUser {
        return registeredUserRepository.findById(id).get()
    }

    fun create(registeredUser: RegisteredUser): RegisteredUser {
        registeredUser.card = Loyalty(user = registeredUser)
        return registeredUserRepository.save(registeredUser)
    }

    fun update(newUserInfo: RegisteredUserDto) : RegisteredUser? {
        var user = registeredUserRepository.findOneByEmail(newUserInfo.email)
        if(user.password.compareTo(newUserInfo.password.toString())!=0)
            return null
        user.updateUserFields(newUserInfo)
        return registeredUserRepository.save(user)
    }

    fun findByEmail(email: String) : RegisteredUser = registeredUserRepository.findOneByEmail(email)

    fun findAll(): List<RegisteredUser> = registeredUserRepository.findAll()

    fun findById(id: Int) : Optional<RegisteredUser> = registeredUserRepository.findById(id)

    fun setEnable(email : String) : RegisteredUser{
        var user = registeredUserRepository.findOneByEmail(email)
        user.updateEnable()
        return registeredUserRepository.save(user)
    }
}