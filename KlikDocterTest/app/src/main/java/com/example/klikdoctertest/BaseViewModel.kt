package com.example.klikdoctertest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.klikdoctertest.utils.mutableSharedFlow
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch


/**
 * @author Thien.
 */
open class BaseViewModel : ViewModel() {
    protected val _loadingFlow = MutableStateFlow(false)
    val loadingFlow: Flow<Boolean> = _loadingFlow
    protected val _errorLiveData = mutableSharedFlow<Throwable>()
    val errorLiveData: Flow<Throwable> = _errorLiveData

    val handler = CoroutineExceptionHandler { _, exception ->
        _loadingFlow.tryEmit(false)
        exception.printStackTrace()
        _errorLiveData.tryEmit(exception)
        handleError(exception)
    }

    open fun handleError(exception: Throwable) {}

    fun async(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch(Dispatchers.IO + handler, block = block)
    }
}