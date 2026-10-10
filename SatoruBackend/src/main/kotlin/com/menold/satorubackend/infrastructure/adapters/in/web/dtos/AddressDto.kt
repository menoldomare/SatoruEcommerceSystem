package com.menold.satorubackend.infrastructure.adapters.`in`.web.dtos

import com.menold.satorubackend.application.ports.`in`.dto.CreateAddressCommand
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

data class AddressDto(
    @field:NotBlank(message = "Country is required")
    val country: String,

    val label: String?, //Home, Work

    val type: String?, //shipping, billing, both

    @field:NotBlank(message = "Street address line 1 is required")
    @field:Size(max = 100, message = "Street line 1 must not exceed 100 characters")
    val streetLine1: String,

    @field:Size(max = 100, message = "Street line 2 must not exceed 100 characters")
    val streetLine2: String? = null,

    @field:NotBlank(message = "City is required")
    @field:Size(max = 50, message = "City must not exceed 50 characters")
    val city: String,

    @field:Size(max = 50, message = "State/Province must not exceed 50 characters")
    val stateOrProvince: String? = null,

    @field:NotBlank(message = "Postal/ZIP code is required")
    @field:Size(max = 20, message = "Postal code must not exceed 20 characters")
    val postalCode: String,

    @field:NotBlank(message = "Country code is required")
    @field:Pattern(
    regexp = "^[A-Z]{2}$",
    message = "Country must be a valid ISO 3166-1 alpha-2 code (e.g. US, KE, GB)")
    val countryCode: String,

    val isPrimary: Boolean = false,

    @field:NotBlank(message = "Recipient name must be provided.")
    val recipientName : String

){
    fun toCommand(): CreateAddressCommand {
        return CreateAddressCommand(
            country = country,
            streetLine1 = streetLine1,
            streetLine2 = streetLine2,
            city = city,
            state = stateOrProvince,
            postalCode = postalCode,
            label = label,
            recipientName = recipientName
        )
    }
}