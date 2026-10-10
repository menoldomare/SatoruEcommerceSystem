package com.menold.satorubackend.application.services

import com.menold.satorubackend.domain.valueobjects.PasswordHash
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class PasswordService(
    private val passwordEncoder: PasswordEncoder,
) {

    fun hash(rawPassword: String): PasswordHash {
//        require(rawPassword.isNotBlank()) { "Password must not be blank" }
        val hash = passwordEncoder.encode(rawPassword) ?: error("Password encoder returned null.")
        return PasswordHash.fromHash(hash)
    }

    fun matches(rawPassword: String, passwordHash: PasswordHash): Boolean {
        return passwordEncoder.matches(rawPassword, passwordHash.value)
    }
}
