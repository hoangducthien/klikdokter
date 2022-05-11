package com.example.klikdoctertest.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.klikdoctertest.BaseActivity
import com.example.klikdoctertest.R
import com.example.klikdoctertest.USER_EXTRA
import com.example.klikdoctertest.databinding.ActivityLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginActivity: BaseActivity<LoginViewModel>() {
    override lateinit var viewModel: LoginViewModel
    private lateinit var binding: ActivityLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.userFlow.collect { user ->
                        if (user != null) {
                            Toast.makeText(this@LoginActivity, getString(R.string.login_successful), Toast.LENGTH_LONG).show()
                            finish()
                        }
                    }
                }
            }
        }
        binding.buttonLogin.setOnClickListener {
            viewModel.login(binding.email.text.toString(), binding.password.text.toString())
        }
    }
}