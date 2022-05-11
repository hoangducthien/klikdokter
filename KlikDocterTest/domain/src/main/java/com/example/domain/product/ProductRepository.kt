package com.example.domain.product

import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    fun getAllProduct(): Flow<List<Product>>

    fun updateProduct(product: Product): Product

    fun addProduct(product: Product): Product

    fun deleteProduct(sku: String): Product

    fun searchProduct(sku: String): List<Product>

}