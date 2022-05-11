package com.example.domain.user

import javax.inject.Inject

class UserRegisterUseCase @Inject constructor() {

    @Inject
    lateinit var userRepository: UserRepository

    fun register(email: String, password: String): User? {
        return userRepository.register(email, password)
    }

}