package com.example.androidsimpleboilerplates.data.remote.repository

import com.example.androidsimpleboilerplates.core.extensions.Resource
import com.example.androidsimpleboilerplates.core.extensions.safeApiCall
import com.example.androidsimpleboilerplates.data.remote.dto.GithubUserResponse
import com.example.androidsimpleboilerplates.data.remote.service.ApiService
import com.example.androidsimpleboilerplates.domain.repository.UsersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UsersRepositoryImp @Inject constructor(private val apiService: ApiService) :
    UsersRepository {

    override suspend fun getPicInfo(): Flow<Resource<List<GithubUserResponse>>> = safeApiCall {
        apiService.getPicInfo()
    }

}