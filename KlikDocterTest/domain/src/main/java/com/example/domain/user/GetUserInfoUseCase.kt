package com.example.domain.user

import javax.inject.Inject

class GetUserInfoUseCase @Inject constructor() {

    @Inject
    lateinit var userRepository: UserRepository

    fun getUserInfo(): User {
        val user = userRepository.getUserInfo()
        UserSession.updateLoggedInUser(user)
        return user
    }

}