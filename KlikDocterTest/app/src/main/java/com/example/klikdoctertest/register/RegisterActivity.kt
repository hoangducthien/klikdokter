package com.example.klikdoctertest.register

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.domain.MIN_PASSWORD_LENGTH
import com.example.domain.base.DomainError
import com.example.domain.base.EMAIL_NOT_VALID
import com.example.domain.base.PASSWORD_TOO_SHORT
import com.example.klikdoctertest.BaseActivity
import com.example.klikdoctertest.R
import com.example.klikdoctertest.USER_EXTRA
import com.example.klikdoctertest.databinding.ActivityRegisterBinding
import com.example.klikdoctertest.login.LoginActivity
import com.example.klikdoctertest.login.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RegisterActivity: BaseActivity<RegisterViewModel>() {
    override lateinit var viewModel: RegisterViewModel
    private lateinit var binding: ActivityRegisterBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        viewModel = ViewModelProvider(this)[RegisterViewModel::class.java]
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.userFlow.collect { user ->
                        if (user != null) {
                            Toast.makeText(this@RegisterActivity, getString(R.string.register_success), Toast.LENGTH_LONG).show()
                            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                            finish()
                        }
                    }
                }
            }
        }
        binding.buttonRegister.setOnClickListener {
            viewModel.register(binding.email.text.toString(), binding.password.text.toString())
        }
    }

    override fun handleError(error: Throwable): Boolean {
        if (error is DomainError) {
            return when (error.errorCode) {
                EMAIL_NOT_VALID -> {
                    Toast.makeText(this@RegisterActivity, getString(R.string.invalid_email), Toast.LENGTH_LONG).show()
                    true
                }
                PASSWORD_TOO_SHORT -> {
                    Toast.makeText(this@RegisterActivity, String.format(getString(R.string.password_too_short), MIN_PASSWORD_LENGTH), Toast.LENGTH_LONG).show()
                    true
                }
                else -> false
            }
        }
        return false
    }
}