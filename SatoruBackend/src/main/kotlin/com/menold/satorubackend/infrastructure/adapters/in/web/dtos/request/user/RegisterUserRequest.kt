package com.menold.satorubackend.infrastructure.adapters.`in`.web.dtos.request.user

import com.menold.satorubackend.application.ports.`in`.dto.RegisterUserCommand
import com.menold.satorubackend.domain.model.Address
import com.menold.satorubackend.domain.valueobjects.Locale

import com.menold.satorubackend.domain.valueobjects.PhoneNumber
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class RegisterUserRequest(
    @field: NotBlank @field:Email
    val email: String,


    val phoneNumber: String?,
    val phoneCountryCode: String?,

    @field: NotBlank @field:Size(min = 8)
    val password: String,

    val locale: String?,
    val currencyPreference: String?,

    val addresses: List<Address> = emptyList(),
)