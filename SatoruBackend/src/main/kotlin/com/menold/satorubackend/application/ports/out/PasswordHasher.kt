package com.menold.satorubackend.application.ports.out

import com.menold.satorubackend.domain.valueobjects.PasswordHash
import com.menold.satorubackend.domain.valueobjects.RawPassword

interface PasswordHasher {
    fun hashPassword(rawPassword: RawPassword): PasswordHash
}