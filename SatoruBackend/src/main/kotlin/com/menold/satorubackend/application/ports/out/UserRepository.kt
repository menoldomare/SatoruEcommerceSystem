package com.menold.satorubackend.application.ports.out

import com.menold.satorubackend.infrastructure.adapters.`in`.web.dtos.response.UserResponse
import com.menold.satorubackend.domain.models.User
import com.menold.satorubackend.domain.valueobjects.Email
import com.menold.satorubackend.domain.valueobjects.PhoneNumber
import com.menold.satorubackend.domain.valueobjects.UserId
import org.springframework.data.repository.Repository

// What the app NEEDS
// This is a CONTRACT - the inner layer defines what it needs
// The outer layer will PROVIDE the implementation

interface UserRepository: Repository<User, Long> {
    fun save(user: User): UserResponse

    fun findById(userId: String): User?
    fun findByEmail(email: Email): User?
    fun findAll(): List<User>
    fun findById(id: UserId): User?
    fun findByPhoneNumber(phoneNumber: PhoneNumber): User?

    fun update(id: UserId): User

    fun existsById(userId: String?): Boolean
    fun existsByEmail(email: String): Boolean
    fun existsByPhoneNumber(phoneNumber: PhoneNumber): Boolean

    fun deleteById(id: UserId)







}

