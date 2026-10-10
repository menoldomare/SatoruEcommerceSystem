package com.menold.satorubackend.infrastructure.adapters.out.persistence.entities.access

import com.menold.satorubackend.domain.UserStatus
import com.menold.satorubackend.domain.model.Address
import com.menold.satorubackend.domain.models.UserRole
import com.menold.satorubackend.domain.valueobjects.Currency
import com.menold.satorubackend.domain.valueobjects.Email
import com.menold.satorubackend.domain.valueobjects.Locale
import com.menold.satorubackend.domain.valueobjects.PasswordHash
import com.menold.satorubackend.domain.valueobjects.PhoneNumber
import com.menold.satorubackend.domain.valueobjects.UserId
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDateTime
import java.util.UUID
import kotlin.time.Instant

@Entity
@Table(name = "users")
class UserEntity(

    @Column(unique = true, nullable = false)
    var email: Email,

    @Column(name = "email_verified", insertable = true)
    val emailVerified: Boolean = false,

    @Column(name = "phone_number", unique = true, nullable = false)
    var phoneNumber: String?,

    @Column(name = "phone_verified")
    var phoneVerified: Boolean = false,

    @Column(name = "password", nullable = false)
    private var passwordHash: String,

    var locale: String = "en-Us",

    @Column(name = "currency_preference")
    var currencyPreference: String = "KSH",


    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    var addresses: List<AddressEntity> = mutableListOf(),

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    var roles: List<UserRoleEntity> = mutableListOf(),

    ){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", updatable = false)
    val id: UUID = UUID.randomUUID()

    @Enumerated(EnumType.STRING)
    var status: UserStatus = UserStatus.ACTIVE

    @Column(name = "last_login_at")
    var lastLoginAt: LocalDateTime? = null

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    val createdAt: LocalDateTime? = null

    @Column(name = "updated_at")
    var updatedAt: Instant? = null

}