package com.example.data.utils

import com.example.data.product.ProductEntity
import com.example.domain.product.Product


fun ProductEntity.toDomainModel(): Product {
    return Product(
        id = id,
        sku = sku,
        productName = productName,
        qty = qty,
        price = price,
        unit = unit,
        image = image,
        status = status,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}

fun Product.toDBModel(): ProductEntity {
    return ProductEntity(
        id = id,
        sku = sku,
        productName = productName,
        qty = qty,
        price = price,
        unit = unit,
        image = image,
        status = status,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}