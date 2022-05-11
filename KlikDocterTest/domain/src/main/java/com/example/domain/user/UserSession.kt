package com.example.domain.user

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

object UserSession {

    private val _currentUserFlow = MutableStateFlow(User())
    val currentUserFlow: Flow<User> = _currentUserFlow
    var currentUser = _currentUserFlow.value

    fun updateLoggedInUser(user: User) {
        currentUser = user
        _currentUserFlow.tryEmit(user)
    }

}