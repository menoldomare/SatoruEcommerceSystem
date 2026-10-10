package com.menold.satorubackend.application.ports.`in`.usecases

import com.menold.satorubackend.infrastructure.adapters.`in`.web.dtos.response.UserResponse
import com.menold.satorubackend.application.exceptions.DuplicateEmailException
import com.menold.satorubackend.application.ports.`in`.dto.RegisterUserCommand

import com.menold.satorubackend.application.ports.out.UserRepository

class `CreateUser.md`(
private val userRepository: UserRepository
) {
operator fun invoke(command: RegisterUserCommand): UserResponse {

//        require(command.passwordHash.value.isNotBlank()) { "Password hash cannot be blank" }
//        require(command.email.value.isNotBlank()) { "Email cannot be blank" }

        if (userRepository.existsByEmail(command.email.value)){
//            throw IllegalArgumentException("Email already exists")
//            throw IllegalStateException("User with the email ${command.email.value} already exists")
throw DuplicateEmailException(command.email.value)
}


        return userRepository.save(command.toDomain())
    }
}


//
//class UserDomainService(
//    private val userRepository: UserRepository,
//    private val passwordHasher: PasswordHasher,
//    private val eventPublisher: DomainEventPublisher
//) {
//    fun registerUser(
//        email: Email,
//        phoneNumber: PhoneNumber?,
//        rawPassword: String,
//        locale: Locale = Locale(),
//        currency: Currency = Currency()
//    ): Result<User> {
//        if (userRepository.existsByEmail(email)) {
//            return Result.failure(DuplicateEmailException("Email already registered: ${email.value}"))
//        }
//
//        phoneNumber?.let {
//            if (userRepository.existsByPhoneNumber(it)) {
//                return Result.failure(DuplicatePhoneException("Phone number already registered: ${it.value}"))
//            }
//        }
//
//        val passwordHash = passwordHasher.hash(rawPassword)
//        val user = User.create(email, phoneNumber, passwordHash, locale, currency)
//
//        userRepository.save(user)
//        eventPublisher.publish(UserCreatedEvent(user.id, user.email))
//
//        return Result.success(user)
//    }
//
//    fun authenticate(email: Email, rawPassword: String): Result<User> {
//        val user = userRepository.findByEmail(email)
//            ?: return Result.failure(UserNotFoundException("User not found"))
//
//        if (user.status != UserStatus.ACTIVE) {
//            return Result.failure(AccountDisabledException("Account is not active"))
//        }
//
//        if (!passwordHasher.verify(rawPassword, user.passwordHash)) {
//            return Result.failure(InvalidCredentialsException("Invalid credentials"))
//        }
//
//        val updatedUser = user.updateLastLogin()
//        userRepository.save(updatedUser)
//        eventPublisher.publish(UserLoggedInEvent(updatedUser.id))
//
//        return Result.success(updatedUser)
//    }
//}
//
//// ==================== EXCEPTIONS ====================
//
//sealed class UserDomainException(message: String) : RuntimeException(message)
//class DuplicateEmailException(message: String) : UserDomainException(message)
//class DuplicatePhoneException(message: String) : UserDomainException(message)
//class UserNotFoundException(message: String) : UserDomainException(message)
//class AccountDisabledException(message: String) : UserDomainException(message)
//class InvalidCredentialsException(message: String) : UserDomainException(message)
//
//// ==================== SUPPORTING INTERFACES ====================
//
//interface PasswordHasher {
//    fun hash(rawPassword: String): PasswordHash
//    fun verify(rawPassword: String, hash: PasswordHash): Boolean
//}
//
//interface DomainEventPublisher {
//    fun publish(event: UserDomainEvent)
//}