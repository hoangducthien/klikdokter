package com.example.data.base

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.product.ProductDao
import com.example.data.product.ProductEntity

@Database(entities = [ProductEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}
