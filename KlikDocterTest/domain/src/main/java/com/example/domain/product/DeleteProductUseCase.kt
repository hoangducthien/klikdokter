package com.example.domain.product

import javax.inject.Inject

class DeleteProductUseCase @Inject constructor() {

    @Inject
    lateinit var productRepository: ProductRepository


    fun deleteProduct(product: Product): Product {
        return productRepository.deleteProduct(product.sku)
    }

}