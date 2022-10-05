package com.example.androidsimpleboilerplates.core.di

import com.example.androidsimpleboilerplates.data.mapper.GithubUserMapper
import com.example.androidsimpleboilerplates.data.remote.repository.UsersRepositoryImp
import com.example.androidsimpleboilerplates.data.remote.service.ApiService
import com.example.androidsimpleboilerplates.domain.repository.UsersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideUsersRepository(service: ApiService, mapper: GithubUserMapper): UsersRepository =
        UsersRepositoryImp(service, mapper)
}