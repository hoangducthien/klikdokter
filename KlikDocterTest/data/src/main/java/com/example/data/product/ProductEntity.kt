package com.example.data.product

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class ProductEntity(@PrimaryKey
                    @ColumnInfo(name = "id")
                    var id: String = "",
                    @ColumnInfo(name = "sku")
                    var sku: String = "",
                    @ColumnInfo(name = "product_name")
                    var productName: String = "",
                    @ColumnInfo(name = "qty")
                    var qty: Int = 0,
                    @ColumnInfo(name = "price")
                    var price: Int = 0,
                    @ColumnInfo(name = "unit")
                    var unit: String = "",
                    @ColumnInfo(name = "image")
                    var image: String? = "",
                    @ColumnInfo(name = "status")
                    var status: Int = 0,
                    @ColumnInfo(name = "created_at")
                    var createdAt: String = "",
                    @ColumnInfo(name = "updated_at")
                    var updatedAt: String = "",
)