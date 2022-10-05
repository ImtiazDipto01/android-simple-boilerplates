package com.example.androidsimpleboilerplates.data.remote.repository

import com.example.androidsimpleboilerplates.core.extensions.Resource
import com.example.androidsimpleboilerplates.core.extensions.safeApiCall
import com.example.androidsimpleboilerplates.data.mapper.GithubUserMapper
import com.example.androidsimpleboilerplates.data.remote.dto.GithubUserResponse
import com.example.androidsimpleboilerplates.data.remote.service.ApiService
import com.example.androidsimpleboilerplates.domain.model.GithubUser
import com.example.androidsimpleboilerplates.domain.repository.UsersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import javax.inject.Inject

class UsersRepositoryImp @Inject constructor(
    private val apiService: ApiService,
    private val mapper: GithubUserMapper
) : UsersRepository {

    override suspend fun getUsers(): Flow<Resource<List<GithubUser>>> = safeApiCall {
        apiService.getUsers()
    }.transform {
        if (it is Resource.Success)
            emit(Resource.Success(mapper.mapFromEntityList(it.data)))
        else emit(it as Resource<List<GithubUser>>)
    }

}