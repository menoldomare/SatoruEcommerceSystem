package com.menold.satorubackend.domain.models

import com.menold.satorubackend.application.services.PasswordService
import com.menold.satorubackend.domain.RoleType
import com.menold.satorubackend.domain.UserStatus
import com.menold.satorubackend.domain.model.Address
import com.menold.satorubackend.domain.valueobjects.AddressId
import com.menold.satorubackend.domain.valueobjects.Currency
import com.menold.satorubackend.domain.valueobjects.Email
import com.menold.satorubackend.domain.valueobjects.Locale
import com.menold.satorubackend.domain.valueobjects.PasswordHash
import com.menold.satorubackend.domain.valueobjects.PhoneNumber
import com.menold.satorubackend.domain.valueobjects.UserId
import java.time.LocalDateTime
import kotlin.time.Clock
import kotlin.time.Instant


data class User(
    val id: UserId,
    val email: Email,
    val emailVerified: Boolean,
    val phoneNumber: PhoneNumber?,
    val phoneVerified: Boolean,
    private val passwordHash: PasswordHash,
    val locale: Locale?,
    val currencyPreference: Currency?,
    val status: UserStatus,
    val addresses: List<Address>,
    val roles: List<UserRole>,
    val lastLoginAt: LocalDateTime?,
    val createdAt: LocalDateTime?,
    val updatedAt: Instant
){
    companion object {
        fun create(
            email: Email,
            phoneNumber: PhoneNumber?,
            passwordHash: PasswordHash,
            locale: Locale? = Locale(),
            currencyPreference: Currency? = Currency(),
            addresses: List<Address>
        ): User {
            return User(
                id = UserId.generate(),
                email = email,
                emailVerified = false,
                phoneNumber = phoneNumber,
                phoneVerified = false,
                passwordHash = passwordHash,
                locale = locale,
                currencyPreference = currencyPreference,
                status = UserStatus.ACTIVE,
                addresses = emptyList(),
                roles = emptyList(),
                lastLoginAt = null,
                createdAt = LocalDateTime.now(),
                updatedAt = Clock.System.now()
            )
        }
    }
    fun verifyEmail(): User = copy(emailVerified = true)

    fun updateEmail(newEmail: Email): User = copy(email = newEmail, emailVerified = false)

    fun verifyPhone(): User = copy(phoneVerified = true)

    fun verifyPassword(rawPassword: String, passwordService: PasswordService): Boolean =
        passwordService.matches(rawPassword, passwordHash)

    fun updateLastLogin(): User = copy(lastLoginAt = LocalDateTime.now())

    fun suspend(): User = copy(status = UserStatus.SUSPENDED)

    fun activate(): User = copy(status = UserStatus.ACTIVE)

    fun addAddress(address: Address): User = copy(addresses = addresses + address)

    fun removeAddress(addressId: AddressId): User =
        copy(addresses = addresses.filter { it.id != addressId })

    fun assignRole(role: UserRole): User = copy(roles = roles + role)

    fun removeRole(roleType: RoleType): User =
        copy(roles = roles.filter { it.roleType != roleType })

    fun changeCurrency(newCurrency: Currency): User =
        copy(currencyPreference = newCurrency)

    fun changeLocale(newLocale: Locale): User =
        copy(locale = newLocale)

    fun updatePhone(newPhone: PhoneNumber): User =
        copy(phoneNumber = newPhone, phoneVerified = false)

}
