package com.example.domain.product

import javax.inject.Inject

class SearchProductUseCase @Inject constructor() {

    @Inject
    lateinit var productRepository: ProductRepository


    fun searchProduct(sku: String): List<Product> {
        return try {
            productRepository.searchProduct(sku)
        } catch (e: Exception) {
            emptyList()
        }
    }

}