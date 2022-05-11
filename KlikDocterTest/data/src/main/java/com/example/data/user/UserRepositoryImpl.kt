package com.example.data.user

import com.example.data.base.USER_CACHE_KEY
import com.example.data.cache.FileCache
import com.example.domain.base.DomainError
import com.example.domain.base.UNKNOWN_ERROR
import com.example.domain.user.User
import com.example.domain.user.UserRepository

class UserRepositoryImpl(private val fileCache: FileCache, private val userService: UserService): UserRepository {

    override fun login(email: String, password: String): User {
        userService.login(email, password).execute().body()?.token?.let {
            val user = User(email, it)
            fileCache.update(USER_CACHE_KEY, user)
            return user
        }
        throw DomainError(UNKNOWN_ERROR)
    }

    override fun register(email: String, password: String): User {
        userService.register(email, password).execute().body()?.user?.let {
            return it
        }
        throw DomainError(UNKNOWN_ERROR)
    }

    override fun getUserInfo(): User {
        return fileCache.get(USER_CACHE_KEY) ?: User()
    }
}