package com.menold.satorubackend.application.ports.`in`

import com.menold.satorubackend.domain.model.Address
import com.menold.satorubackend.domain.models.User
import com.menold.satorubackend.domain.valueobjects.Email
import com.menold.satorubackend.domain.valueobjects.Locale
import com.menold.satorubackend.domain.valueobjects.PasswordHash
import com.menold.satorubackend.domain.valueobjects.PhoneNumber

// This defines the contract for what operations are available
// Input ports define What the app can DO
// Controllers will use this interface
interface UserInputPort {
    fun create(command: CreateUserCommand): User
    fun update()
    fun find()
    fun findByEmail(email: String): User
    fun delete()
    fun deleteByID(id: String): User

    data class CreateUserCommand(
        val email: Email,
        val phoneNumber: PhoneNumber?,
        val passwordHash: PasswordHash,
        val locale: Locale?,
        val addresses: List<Address>?,
    )
    data class UpdateUserCommand(
        val email: Email,
        val phoneNumber: PhoneNumber?,
        val passwordHash: PasswordHash,
        val locale: Locale?,
        val addresses: List<Address>?,
    )

}