package com.example.data.product

import com.example.data.base.BaseResponse
import com.example.domain.product.Product
import com.google.gson.annotations.SerializedName

class ProductResponse: BaseResponse() {
    @SerializedName("data")
    val product: Product? = null

}