package com.example.retrofitproject.di

import com.example.retrofitproject.repository.MainRepository
import com.example.retrofitproject.retrofit.BlogRetrofit
import com.example.retrofitproject.retrofit.NetworkMapper
import com.example.retrofitproject.room.BlogDao
import com.example.retrofitproject.room.CacheMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object RepositoryModule {
    @Singleton
    @Provides
    fun provideMainRepository(
        blogDao: BlogDao,
        retrofit: BlogRetrofit,
        cacheMapper: CacheMapper,
        networkMapper: NetworkMapper
    ): MainRepository{
        return MainRepository(
            blogDao,
            retrofit,
            cacheMapper,
            networkMapper
        )
    }
}