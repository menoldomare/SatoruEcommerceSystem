package com.menold.satorubackend.application.ports.`in`.dto

import com.menold.satorubackend.domain.model.Address

data class CreateAddressCommand(
    val country: String,
    val streetLine1: String,
    val streetLine2: String?,
    val city: String,
    val state: String?,
    val postalCode: String?,
    val label: String? = null, // e.g., "Home", "Work"
    val recipientName: String
){
    fun toDomain(): Address {
        return Address.create(
            city = city,
            country = country,
            state = state,
            postalCode = postalCode,
            label = label,
            streetLine1 = streetLine1,
            streetLine2 = streetLine2,
            recipientName = recipientName
        )
    }
}
