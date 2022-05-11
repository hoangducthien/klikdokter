package com.example.klikdoctertest.register

import com.example.domain.base.DomainError
import com.example.domain.base.EMAIL_NOT_VALID
import com.example.domain.user.User
import com.example.domain.user.UserRegisterUseCase
import com.example.klikdoctertest.BaseViewModel
import com.example.domain.validateEmail
import com.example.domain.validatePassword
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor() : BaseViewModel() {

    @Inject
    lateinit var userRegisterUseCase: UserRegisterUseCase

    private val _userFlow = MutableStateFlow<User?>(null)
    var userFlow: Flow<User?> = _userFlow


    fun register(email: String, password: String) {
        if (!validateEmail(email)) {
            _errorLiveData.tryEmit(DomainError(EMAIL_NOT_VALID))
            return
        } else {
            val passwordCheck = validatePassword(password)
            if (passwordCheck != 0) {
                _errorLiveData.tryEmit(DomainError(passwordCheck))
                return
            }
        }

        _loadingFlow.tryEmit(true)
        async {
            _userFlow.tryEmit(userRegisterUseCase.register(email, password))
            _loadingFlow.tryEmit(false)
        }
    }


}