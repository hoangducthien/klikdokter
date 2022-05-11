package com.example.data.hiltmodules

import com.example.data.cache.FileCache
import com.example.data.product.ProductDao
import com.example.data.product.ProductRepositoryImpl
import com.example.data.product.ProductService
import com.example.data.user.UserRepositoryImpl
import com.example.data.user.UserService
import com.example.domain.product.ProductRepository
import com.example.domain.user.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideUserRepository(fileCache: FileCache, userService: UserService): UserRepository {
        return UserRepositoryImpl(fileCache, userService)
    }

    @Provides
    @ViewModelScoped
    fun provideProductRepository(productDao: ProductDao, productService: ProductService): ProductRepository {
        return ProductRepositoryImpl(productDao, productService)
    }

}