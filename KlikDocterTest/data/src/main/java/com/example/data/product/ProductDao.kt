package com.example.data.product

import androidx.room.*

@Dao
interface ProductDao {

    @Query("SELECT * FROM ProductEntity")
    fun getAll(): List<ProductEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(product: ProductEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(products: List<ProductEntity>)

    @Delete
    fun delete(productEntity: ProductEntity)

}