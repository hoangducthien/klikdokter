package com.example.data.product

import com.example.data.utils.toDBModel
import com.example.data.utils.toDomainModel
import com.example.domain.base.DomainError
import com.example.domain.base.UNKNOWN_ERROR
import com.example.domain.product.Product
import com.example.domain.product.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ProductRepositoryImpl(private val productDao: ProductDao, private val productService: ProductService): ProductRepository {

    override fun getAllProduct(): Flow<List<Product>> {
        return flow {
            emit(productDao.getAll().map {
                it.toDomainModel()
            })
            productService.getProducts().execute()?.let {
                val products = it.body()
                if (products != null) {
                    productDao.insertAll(products.map { product ->
                        product.toDBModel()
                    })
                    emit(products)
                } else {
                    throw DomainError(UNKNOWN_ERROR)
                }
            }
        }
    }

    override fun updateProduct(product: Product): Product {
        productService.updateProduct(product).execute().body()?.let {
            productDao.insert(it.toDBModel())
            return it
        }
        throw DomainError(UNKNOWN_ERROR)
    }

    override fun addProduct(product: Product): Product {
        productService.addProduct(product).execute().body()?.let {
            productDao.insert(it.toDBModel())
            return it
        }
        throw DomainError(UNKNOWN_ERROR)
    }

    override fun deleteProduct(sku: String): Product {
        productService.deleteProduct(sku).execute().body()?.let {
            productDao.delete(it.toDBModel())
            return it
        }
        throw DomainError(UNKNOWN_ERROR)
    }

    override fun searchProduct(sku: String): List<Product> {
        productService.getProductsBySku(sku).execute().body()?.let {
            if (it.id.isNotEmpty()) {
                return listOf(it)
            }
        }
        return emptyList()
    }
}