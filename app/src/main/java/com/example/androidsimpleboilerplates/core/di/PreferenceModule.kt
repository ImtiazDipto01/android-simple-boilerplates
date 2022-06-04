package com.example.androidsimpleboilerplates.core.di

import android.content.Context
import android.content.SharedPreferences
import com.example.androidsimpleboilerplates.core.extensions.APP_PREF
import com.example.androidsimpleboilerplates.data.local.preference.AppPreference
import com.example.androidsimpleboilerplates.data.local.preference.PreferenceHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PreferenceModule {

    @Provides
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences =
        context.getSharedPreferences(
            APP_PREF, Context.MODE_PRIVATE
        )

    @Singleton
    @Provides
    fun providePreferenceHelper(appPreferenceHelper: AppPreference): PreferenceHelper =
        appPreferenceHelper

}