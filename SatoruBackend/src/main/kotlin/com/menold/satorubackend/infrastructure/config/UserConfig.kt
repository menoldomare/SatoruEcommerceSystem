package com.menold.satorubackend.infrastructure.config

import com.menold.satorubackend.application.ports.out.UserRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class UserConfig {
    @Bean
    fun createUser(userRepository: UserRepository): `CreateUser.md` = `CreateUser.md`(userRepository)

}