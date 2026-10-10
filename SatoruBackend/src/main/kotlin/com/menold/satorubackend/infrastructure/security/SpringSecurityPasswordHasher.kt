package com.menold.satorubackend.infrastructure.security

import com.menold.satorubackend.application.ports.out.PasswordHasher
import com.menold.satorubackend.domain.valueobjects.PasswordHash
import com.menold.satorubackend.domain.valueobjects.RawPassword
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component


@Component
class SpringSecurityPasswordHasher(
    private val passwordEncoder: PasswordEncoder
): PasswordHasher {

    override fun hashPassword(rawPassword: RawPassword): PasswordHash {
        val encoded = requireNotNull(
            passwordEncoder.encode(rawPassword.value)
        ){
            "Password hashing error: Password is required"
        }
        return PasswordHash.fromHash(encoded)
    }

}