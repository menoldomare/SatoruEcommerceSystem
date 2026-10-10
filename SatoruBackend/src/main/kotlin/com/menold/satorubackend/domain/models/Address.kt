package com.menold.satorubackend.domain.model

import com.menold.satorubackend.domain.AddressType
import com.menold.satorubackend.domain.valueobjects.UserId
import com.menold.satorubackend.domain.valueobjects.AddressId

data class Address(
    val id: AddressId,
    val country: String,
    val label: String? = null, // e.g., "Home", "Work"
    val type: AddressType?,
    val streetLine1: String,
    val streetLine2: String?,
    val recipientName: String,
    val city: String,
    val state: String?,
    val postalCode: String?,
    val isDefaultShipping: Boolean = false,
    val isDefaultBilling: Boolean = false,
    val geoLat: Double? = null,
    val geoLng: Double? = null

) {
    init {
        require(streetLine1.isNotBlank()) { "Street line1 cannot be blank" }
        require(city.isNotBlank()) { "City cannot be blank" }
        require(country.isNotBlank()) { "Country cannot be blank" }
        require(recipientName.isNotBlank()) { "Recipient name must be provided" }
    }

    companion object {
        fun create(
            country: String,
            city: String,
            state: String? = null,
            streetLine1: String,
            streetLine2: String? = null,
            postalCode: String? = null,
            label: String? = null,
            recipientName: String
        ): Address = Address(
            id = AddressId.generate(),
            streetLine1 = streetLine1,
            streetLine2 = streetLine2,
            city = city,
            state = state,
            postalCode = postalCode,
            country = country,
            label = label,
            type = AddressType.SHIPPING,
            recipientName = recipientName,
            isDefaultShipping = false,
            isDefaultBilling = false,
            geoLat = null,
            geoLng = null
        )
    }

    fun markAsDefault(): Address = copy(isDefaultShipping = true, isDefaultBilling = true)
    fun removeDefault(): Address = copy(isDefaultShipping = false, isDefaultBilling = false)
}

