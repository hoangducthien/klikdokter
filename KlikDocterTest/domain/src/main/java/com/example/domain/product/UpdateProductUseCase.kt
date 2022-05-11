package com.example.domain.product

import javax.inject.Inject

class UpdateProductUseCase @Inject constructor() {

    @Inject
    lateinit var productRepository: ProductRepository


    fun updateProduct(product: Product): Product {
        return productRepository.updateProduct(product)
    }

}