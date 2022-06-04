package com.example.androidsimpleboilerplates.core.di

import android.content.Context
import androidx.room.Room
import com.example.androidsimpleboilerplates.data.local.db.AppDatabase
import com.example.androidsimpleboilerplates.data.local.db.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideUserDao(appDatabae: AppDatabase): UserDao = appDatabae.userDao()
}