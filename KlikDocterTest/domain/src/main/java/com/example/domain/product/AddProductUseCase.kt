package com.example.domain.product

import javax.inject.Inject

class AddProductUseCase @Inject constructor() {

    @Inject
    lateinit var productRepository: ProductRepository


    fun addProduct(product: Product): Product {
        return productRepository.addProduct(product)
    }

}