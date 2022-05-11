package com.example.klikdoctertest.home

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.user.User
import com.example.domain.user.UserSession
import com.example.klikdoctertest.BaseActivity
import com.example.klikdoctertest.R
import com.example.klikdoctertest.databinding.ActivityHomeBinding
import com.example.klikdoctertest.login.LoginActivity
import com.example.klikdoctertest.register.RegisterActivity
import com.example.klikdoctertest.utils.showProductUpdateDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeActivity : BaseActivity<HomeViewModel>() {
    override lateinit var viewModel: HomeViewModel
    private lateinit var binding: ActivityHomeBinding
    private var productListAdapter = ProductListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        binding = ActivityHomeBinding.inflate(layoutInflater)
        binding.productList.layoutManager = LinearLayoutManager(this)
        binding.productList.adapter = productListAdapter
        val dividerItemDecoration = DividerItemDecoration(
            this,
            RecyclerView.VERTICAL
        )
        binding.productList.addItemDecoration(dividerItemDecoration)

        productListAdapter.onEditCallback = {
            viewModel.updateProduct(it)
        }
        productListAdapter.onDeleteCallback = {
            viewModel.deleteProduct(it)
        }
        setContentView(binding.root)
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.userFlow.collect { user ->
                        if (user?.isLoggedIn() == true) {
                            bindUserInfo(user)
                        } else {
                            binding.homeRegister.visibility = View.VISIBLE
                            binding.homeRegister.setOnClickListener {
                                startActivity(Intent(this@HomeActivity, RegisterActivity::class.java))
                            }
                            binding.homeLogin.setOnClickListener {
                                startActivity(Intent(this@HomeActivity, LoginActivity::class.java))
                            }
                            binding.homeAddProduct.setTextColor(ContextCompat.getColor(this@HomeActivity, R.color.grey))
                            binding.homeAddProduct.setOnClickListener(null)
                            binding.homeSearch.isEnabled = false
                        }
                    }
                }
                launch {
                    UserSession.currentUserFlow.collect { user ->
                        if (user.isLoggedIn()) {
                            bindUserInfo(user)
                        }
                    }
                }
                launch {
                    viewModel.productFlow.collect {
                        it?.let {
                            productListAdapter.updateProductList(it)
                        }
                        if (it.isNullOrEmpty()) {
                            binding.productList.visibility = View.GONE
                        } else {
                            binding.productList.visibility = View.VISIBLE
                        }
                    }
                }
            }
        }
        lifecycleScope.launch {
            viewModel.productUpdated.collect {
                productListAdapter.updateProduct(it)
            }
        }
        lifecycleScope.launch {
            viewModel.productDeleted.collect {
                productListAdapter.removeProduct(it)
            }
        }
        viewModel.getUserInfo()
    }

    private fun bindUserInfo(user: User) {
        binding.homeRegister.visibility = View.GONE
        binding.homeLogin.text =
            String.format(getString(R.string.home_welcome), user.email)
        binding.homeAddProduct.setTextColor(ContextCompat.getColor(this@HomeActivity, R.color.black))
        binding.homeSearch.isEnabled = true
        binding.homeLogin.setOnClickListener(null)
        binding.homeAddProduct.setOnClickListener {
            showProductUpdateDialog(this, null) {
                viewModel.addProduct(it)
            }
        }
        binding.homeSearch.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable) {
                viewModel.keywordSearchFlow.tryEmit(p0.toString())
            }

        })
        viewModel.getProducts()
    }

}