package com.example.domain.product

import com.google.gson.annotations.SerializedName

class Product(@SerializedName("id")
              var id: String = "",
              @SerializedName("sku")
              var sku: String = "",
              @SerializedName("product_name")
              var productName: String = "",
              @SerializedName("qty")
              var qty: Int = 0,
              @SerializedName("price")
              var price: Int = 0,
              @SerializedName("unit")
              var unit: String = "",
              @SerializedName("image")
              var image: String? = "",
              @SerializedName("status")
              var status: Int = 0,
              @SerializedName("created_at")
              var createdAt: String = "",
              @SerializedName("updated_at")
              var updatedAt: String = "",
) {

    fun update(product: Product) {
        id = product.id
        sku = product.sku
        productName = product.productName
        qty = product.qty
        price = product.price
        unit = product.unit
        image = product.image
        status = product.status
        createdAt = product.createdAt
        updatedAt = product.updatedAt
    }

}