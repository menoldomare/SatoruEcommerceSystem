package com.menold.satorubackend.domain.valueobjects

@JvmInline
//value class Email(val value: String) {
//    companion object {
//        private val EMAIL_REGEX = Regex(
//            "[a-zA-Z0-9]+@[a-z]+.[a-z]{2,3}"
//        )
//        private val EMAIL_REGEXw = Regex(
//            "^[a-zA-Z0-9.!#\$%&'*+/=?^_`{|}~-]+@" +
//                    "[a-zA-Z0-9]" +
//                    "(?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?" +
//                    "(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$"
//        )
//
//    }
//}

value class Email(val value: String) {
    init {
        require(value.isNotBlank()) { "Email cannot be blank" }
        require(value.matches(Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"))) { "Invalid email format" }
    }
}