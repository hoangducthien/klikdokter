package com.example.domain.product

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllProductUseCase @Inject constructor() {

    @Inject
    lateinit var productRepository: ProductRepository


    fun getAllProducts(): Flow<List<Product>> {
        return productRepository.getAllProduct()
    }

}