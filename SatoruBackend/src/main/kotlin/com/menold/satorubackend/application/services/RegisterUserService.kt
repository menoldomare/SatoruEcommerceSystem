package com.menold.satorubackend.application.services

import com.menold.satorubackend.application.exceptions.DuplicateEmailException
import com.menold.satorubackend.application.ports.`in`.dto.RegisterUserCommand
import com.menold.satorubackend.application.ports.`in`.usecases.RegisterUserUseCase
import com.menold.satorubackend.application.ports.out.PasswordHasher
import com.menold.satorubackend.application.ports.out.UserRepository
import com.menold.satorubackend.domain.models.User
import com.menold.satorubackend.infrastructure.adapters.`in`.web.dtos.response.UserResponse
import org.springframework.stereotype.Service

@Service
class RegisterUserService(
    private val userRepository: UserRepository,
    private val passwordHasher: PasswordHasher
): RegisterUserUseCase {
    override fun invoke(command: RegisterUserCommand): UserResponse {
        if(userRepository.existsByEmail(email = command.email.value)){
            throw DuplicateEmailException(command.email.value)
        }

        val _passwordHash = passwordHasher.hashPassword(command.rawPassword)

        val user = User.create(
            email = command.email,
            phoneNumber = command.phoneNumber,
            passwordHash = _passwordHash,
            locale = command.locale,
            addresses = command.addresses
        )

        return userRepository.save(user)
    }
}