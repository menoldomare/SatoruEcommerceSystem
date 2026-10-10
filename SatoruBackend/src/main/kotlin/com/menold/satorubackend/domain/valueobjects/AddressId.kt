package com.menold.satorubackend.domain.valueobjects

import java.util.UUID

@JvmInline
value class AddressId(val value: UUID) {
    companion object { fun generate(): AddressId = AddressId(UUID.randomUUID()) }
}
