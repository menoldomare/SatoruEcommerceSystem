package com.menold.satorubackend.domain.valueobjects

@JvmInline
value class Currency(val value: String = "KSH") {
    init {
        require(value.length == 3) { "Currency must be ISO 4217 format (3 letters)" }
    }
}