package com.example.klikdoctertest

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.net.SocketException
import java.net.SocketTimeoutException

abstract class BaseActivity<T: BaseViewModel>: AppCompatActivity() {

    abstract var viewModel: T
    private var activityCallback: ((result: ActivityResult) -> Unit)? = null
    private lateinit var activityLauncher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            activityCallback?.invoke(result)
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.errorLiveData.collect {
                        handleCommonError(it)
                    }
                }
                launch {
                    viewModel.loadingFlow.collect {
                        if (it) {
                            findViewById<View>(R.id.loading)?.visibility = View.VISIBLE
                        } else {
                            findViewById<View>(R.id.loading)?.visibility = View.GONE
                        }
                    }
                }
            }
        }
    }

    private fun handleCommonError(error: Throwable) {
        if (error is SocketException || error is SocketTimeoutException) {
            Toast.makeText(this, getString(R.string.network_error), Toast.LENGTH_LONG).show()
        } else if (!handleError(error)) {
            Toast.makeText(this, getString(R.string.something_went_wrong), Toast.LENGTH_LONG).show()
        }
    }

    // handle error code return from server
    open fun handleError(error: Throwable): Boolean {
        return false
    }

    protected fun launchForResult(intent: Intent, callback: (result: ActivityResult) -> Unit) {
        activityCallback = callback
        activityLauncher.launch(intent)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}