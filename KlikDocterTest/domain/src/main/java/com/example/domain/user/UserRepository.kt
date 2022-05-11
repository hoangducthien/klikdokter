package com.example.domain.user

interface UserRepository {

    fun login(email: String, password: String): User

    fun register(email: String, password: String): User

    fun getUserInfo(): User

}