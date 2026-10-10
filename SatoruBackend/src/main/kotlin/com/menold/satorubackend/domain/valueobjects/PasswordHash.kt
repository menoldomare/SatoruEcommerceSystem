package com.menold.satorubackend.domain.valueobjects


@JvmInline
value class PasswordHash private constructor(val value: String) {
    companion object {
        fun fromHash(hash: String): PasswordHash {
            require(hash.isNotBlank()) { "Password can not be empty" }
            return PasswordHash(hash)
        }
    }
    override fun toString(): String = "PasswordHash(***)"

}

//The application service should then pass the raw password to a password-hashing abstraction and use the resulting PasswordHash to create the user.