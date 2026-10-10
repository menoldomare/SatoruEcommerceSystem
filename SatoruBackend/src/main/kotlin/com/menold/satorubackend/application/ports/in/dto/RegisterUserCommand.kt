package com.menold.satorubackend.application.ports.`in`.dto

import com.menold.satorubackend.domain.model.Address
import com.menold.satorubackend.domain.valueobjects.Email
import com.menold.satorubackend.domain.valueobjects.Locale
import com.menold.satorubackend.domain.valueobjects.PhoneNumber
import com.menold.satorubackend.domain.valueobjects.RawPassword

data class RegisterUserCommand(
    val email: Email,
    val phoneNumber: PhoneNumber?,
    val rawPassword: RawPassword,
    val locale: Locale?,
    val addresses: List<Address>
){
//    fun toDomain(): User {
//        return User.create(
//            email = email,
//            phoneNumber = phoneNumber,
//            passwordHash = ,
//            locale = locale,
//            addresses = addresses
//        )
//    }
}