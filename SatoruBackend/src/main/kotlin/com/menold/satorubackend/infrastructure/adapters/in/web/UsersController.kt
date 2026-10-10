package com.menold.satorubackend.infrastructure.adapters.`in`.web

import com.menold.satorubackend.application.ports.`in`.dto.RegisterUserCommand
import com.menold.satorubackend.application.services.RegisterUserService
import com.menold.satorubackend.infrastructure.adapters.`in`.web.dtos.response.UserResponse
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/users"])
class UsersController(
    private val userService: RegisterUserService
) {
    @GetMapping("/")
    fun getUsers(): String {
        return "Trying to get the system's users"
    }

    @PostMapping("/register")
    fun registerUser(@Valid @RequestBody request : RegisterUserCommand): UserResponse {
        return userService(request)
    }
}
