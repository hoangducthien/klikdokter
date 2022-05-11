package com.example.klikdoctertest.home

import com.example.domain.product.*
import com.example.domain.user.GetUserInfoUseCase
import com.example.domain.user.User
import com.example.domain.user.UserSession
import com.example.klikdoctertest.BaseViewModel
import com.example.klikdoctertest.utils.mutableSharedFlow
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : BaseViewModel() {

    @Inject
    lateinit var getUserInfoUseCase: GetUserInfoUseCase

    @Inject
    lateinit var updateProductUseCase: UpdateProductUseCase

    @Inject
    lateinit var addProductUseCase: AddProductUseCase

    @Inject
    lateinit var deleteProductUseCase: DeleteProductUseCase

    @Inject
    lateinit var getAllProductUseCase: GetAllProductUseCase

    @Inject
    lateinit var searchProductUseCase: SearchProductUseCase

    private val _userFlow = MutableStateFlow<User?>(null)
    var userFlow: Flow<User?> = _userFlow

    private val _productFlow = MutableStateFlow<List<Product>?>(null)
    var productFlow: Flow<List<Product>?> = _productFlow

    private val _productUpdated = mutableSharedFlow<Product>()
    var productUpdated: Flow<Product> = _productUpdated

    private val _productDeleted = mutableSharedFlow<Product>()
    var productDeleted: Flow<Product> = _productDeleted

    private var products: ArrayList<Product> = ArrayList()

    val keywordSearchFlow = mutableSharedFlow<String>()

    init {
        async {
            keywordSearchFlow.debounce(300).collect {
                if (it.isEmpty()) {
                    _productFlow.tryEmit(products)
                } else {
                    _loadingFlow.tryEmit(true)
                    _productFlow.tryEmit(searchProductUseCase.searchProduct(it))
                }
                _loadingFlow.tryEmit(false)
            }
        }
    }

    fun getUserInfo() {
        _loadingFlow.tryEmit(true)
        async {
            val user = getUserInfoUseCase.getUserInfo()
            _userFlow.tryEmit(user)
            if (!user.isLoggedIn()) {
                _loadingFlow.tryEmit(false)
            }
        }
    }

    fun getProducts() {
        _loadingFlow.tryEmit(true)
        async {
            getAllProductUseCase.getAllProducts().collect {
                products = ArrayList(it)
                _productFlow.tryEmit(it)
            }
            _loadingFlow.tryEmit(false)
        }
    }

    fun updateProduct(product: Product) {
        _loadingFlow.tryEmit(true)
        async {
            _productUpdated.tryEmit(updateProductUseCase.updateProduct(product))
            _loadingFlow.tryEmit(false)
        }
    }

    fun addProduct(product: Product) {
        _loadingFlow.tryEmit(true)
        async {
            _productUpdated.tryEmit(addProductUseCase.addProduct(product))
            _loadingFlow.tryEmit(false)
        }
    }

    fun deleteProduct(product: Product) {
        _loadingFlow.tryEmit(true)
        async {
            _productDeleted.tryEmit(deleteProductUseCase.deleteProduct(product))
            _loadingFlow.tryEmit(false)
            products.find { it.sku == product.sku }?.let {
                products.remove(it)
            }
        }
    }


}