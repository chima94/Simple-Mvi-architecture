package com.example.retrofitproject.di

import android.content.Context
import androidx.room.Room
import com.example.retrofitproject.room.BlogDao
import com.example.retrofitproject.room.BlogDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton


@InstallIn(ApplicationComponent::class)
@Module
class RoomModule {

    @Singleton
    @Provides
    fun ProvideBlogDb(@ApplicationContext context: Context) : BlogDatabase{
        return Room.databaseBuilder(
            context,
            BlogDatabase::class.java,
            BlogDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideBlogDao(blogDatabase: BlogDatabase) : BlogDao{
        return blogDatabase.blogDao()
    }
}