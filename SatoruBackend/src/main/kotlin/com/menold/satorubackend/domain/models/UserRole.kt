package com.menold.satorubackend.domain.models

import com.menold.satorubackend.domain.RoleType
import com.menold.satorubackend.domain.valueobjects.UserId
import com.menold.satorubackend.domain.valueobjects.UserRoleId
import java.time.LocalDateTime

data class UserRole(
    val id: UserRoleId,
    val userId: UserId,
    val roleType: RoleType,
    val assignedAt: LocalDateTime,
    val assignedBy: UserId?,
    val expiresAt: LocalDateTime? )
{
    companion object {
        fun assign( userId: UserId, roleType: RoleType, assignedBy: UserId? = null, expiresAt: LocalDateTime? = null ): UserRole =
            UserRole( id = UserRoleId.generate(), userId = userId, roleType = roleType, assignedAt = LocalDateTime.now(), assignedBy = assignedBy, expiresAt = expiresAt )
    }

    fun isExpired(): Boolean = expiresAt?.isBefore(LocalDateTime.now()) ?: false
    fun isActive(): Boolean = !isExpired()
}
