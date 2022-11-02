package com.isa.centarzatransfuzijukrvi.service

import com.isa.centarzatransfuzijukrvi.model.RegisteredUser
import com.isa.centarzatransfuzijukrvi.repository.RegisteredUserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

@Service
class RegisteredUserService(val registeredUserRepository: RegisteredUserRepository) {

    fun findOne(id: Int): RegisteredUser {
        return registeredUserRepository.getOne(id)
    }

    fun create(registeredUser: RegisteredUser): RegisteredUser {
        if (registeredUser.id != null) {
            throw Exception("Id must be null")
        }
        return registeredUserRepository.save(registeredUser)
    }
}