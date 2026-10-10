package com.menold.satorubackend.infrastructure.adapters.`in`.web.dtos.response

import com.menold.satorubackend.domain.UserStatus
import com.menold.satorubackend.domain.model.Address
import com.menold.satorubackend.domain.models.UserRole
import com.menold.satorubackend.domain.valueobjects.Currency
import com.menold.satorubackend.domain.valueobjects.Email
import com.menold.satorubackend.domain.valueobjects.Locale
import com.menold.satorubackend.domain.valueobjects.PasswordHash
import com.menold.satorubackend.domain.valueobjects.PhoneNumber

data class UserResponse(
    val email: Email,
    val phoneNumber: PhoneNumber,
    private val passwordHash: PasswordHash,
    val locale: Locale,
    val currencyPreference: Currency,
    val status: UserStatus,
    val addresses: List<Address>,
    val roles: List<UserRole>,
)