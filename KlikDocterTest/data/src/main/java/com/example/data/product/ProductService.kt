package com.example.data.product

import com.example.domain.product.Product
import retrofit2.Call
import retrofit2.http.*


interface ProductService {

    @POST("api/item/add")
    fun addProduct(@Body product: Product): Call<Product>

    @POST("api/item/update")
    fun updateProduct(@Body product: Product): Call<Product>

    @FormUrlEncoded
    @POST("api/item/delete")
    fun deleteProduct(@Field("sku") sku: String): Call<Product>

    @GET("api/items")
    fun getProducts(): Call<List<Product>>

    @FormUrlEncoded
    @POST("api/item/search")
    fun getProductsBySku(@Field("sku") sku: String): Call<Product>
}