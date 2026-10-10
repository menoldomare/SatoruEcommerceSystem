package com.menold.satorubackend.application.ports.`in`.usecases

import com.menold.satorubackend.application.ports.`in`.dto.RegisterUserCommand
import com.menold.satorubackend.infrastructure.adapters.`in`.web.dtos.response.UserResponse

interface RegisterUserUseCase{
    operator fun invoke(command: RegisterUserCommand): UserResponse
}