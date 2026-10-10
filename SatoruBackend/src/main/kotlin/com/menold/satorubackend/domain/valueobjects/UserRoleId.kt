package com.menold.satorubackend.domain.valueobjects

import java.util.UUID

@JvmInline
value class UserRoleId(val value: UUID) {
    companion object { fun generate(): UserRoleId = UserRoleId(UUID.randomUUID()) }
}
