package com.menold.satorubackend.domain.valueobjects

import kotlin.math.min


@JvmInline
value class RawPassword private constructor(val value: String) {
    companion object {
        private const val MIN_LENGTH = 8
        fun of(rawPassword: String): RawPassword{
            require(rawPassword.isNotBlank()) {
                "Password must not be blank" }
            require(rawPassword.length >= MIN_LENGTH) {
                "Password must contain at least $MIN_LENGTH characters" }

            return RawPassword(rawPassword)
        }
    }
    override fun toString(): String = "RawPassword([REACTED])"
}