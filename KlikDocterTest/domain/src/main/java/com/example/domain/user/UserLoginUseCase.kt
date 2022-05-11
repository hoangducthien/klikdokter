package com.example.domain.user

import javax.inject.Inject

class UserLoginUseCase @Inject constructor() {

    @Inject
    lateinit var userRepository: UserRepository

    fun login(email: String, password: String): User {
        val user = userRepository.login(email, password)
        UserSession.updateLoggedInUser(user)
        return user
    }

}