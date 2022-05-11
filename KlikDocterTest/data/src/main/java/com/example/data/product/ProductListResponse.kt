package com.example.data.product

import com.example.data.base.BaseResponse
import com.example.domain.product.Product
import com.google.gson.annotations.SerializedName

class ProductListResponse: BaseResponse() {
    @SerializedName("data")
    val products: List<Product>? = null

}