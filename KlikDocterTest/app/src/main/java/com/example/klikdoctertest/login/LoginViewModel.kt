package com.example.klikdoctertest.login

import com.example.domain.user.User
import com.example.domain.user.UserLoginUseCase
import com.example.klikdoctertest.BaseViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(): BaseViewModel() {

    @Inject
    lateinit var userLoginUseCase: UserLoginUseCase

    private val _userFlow = MutableStateFlow<User?>(null)
    var userFlow: Flow<User?> = _userFlow


    fun login(email: String, password: String) {
        _loadingFlow.tryEmit(true)
        async {
            _userFlow.tryEmit(userLoginUseCase.login(email, password))
            _loadingFlow.tryEmit(false)
        }
    }


}