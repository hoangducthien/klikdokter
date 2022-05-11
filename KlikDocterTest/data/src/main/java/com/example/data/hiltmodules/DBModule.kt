package com.example.data.hiltmodules

import android.content.Context
import androidx.room.Room
import com.example.data.base.AppDatabase
import com.example.data.cache.FileCache
import com.example.data.product.ProductDao
import com.example.domain.user.User
import com.example.domain.user.UserSession
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DBModule {

    private var currentUserEmail = ""
    private var cacheDB: AppDatabase? = null

    @Provides
    @Singleton
    fun provideFileCache(@ApplicationContext context: Context): FileCache {
        return FileCache(context)
    }

    // different db for different logged in user
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): AppDatabase {
        val user = UserSession.currentUser
        if (user.email != currentUserEmail || cacheDB == null) {
            currentUserEmail = user.email ?: ""
            cacheDB = Room.databaseBuilder(
                context,
                AppDatabase::class.java, "database_$currentUserEmail"
            ).build()
        }
        return cacheDB!!
    }

    @Provides
    fun provideProductDao(appDatabase: AppDatabase): ProductDao {
        return appDatabase.productDao()
    }

}