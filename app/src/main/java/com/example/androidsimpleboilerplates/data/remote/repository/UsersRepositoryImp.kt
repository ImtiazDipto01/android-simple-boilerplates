package com.example.androidsimpleboilerplates.data.remote.repository

import com.example.androidsimpleboilerplates.core.extensions.SafeCacheStrategyHandler
import com.example.androidsimpleboilerplates.core.extensions.Resource
import com.example.androidsimpleboilerplates.core.extensions.SafeApiCallStrategyHandler
import com.example.androidsimpleboilerplates.data.local.db.dao.UserDao
import com.example.androidsimpleboilerplates.data.mapper.GithubUserMapper
import com.example.androidsimpleboilerplates.data.remote.service.ApiService
import com.example.androidsimpleboilerplates.data.local.db.entity.GithubUser
import com.example.androidsimpleboilerplates.data.remote.dto.GithubUserResponse
import com.example.androidsimpleboilerplates.domain.repository.UsersRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class UsersRepositoryImp @Inject constructor(
    private val apiService: ApiService,
    private val mapper: GithubUserMapper,
    private val dao: UserDao
) : UsersRepository {

    override suspend fun getUsers(since: Int): Flow<Resource<List<GithubUser>>> {
        return object : SafeApiCallStrategyHandler<List<GithubUser>, List<GithubUserResponse>>() {

            override suspend fun fetchRemoteData(): Response<List<GithubUserResponse>>  =
                apiService.getUsers(since)

            override fun mapRequestToResult(request: Any): List<GithubUser> =
                mapper.mapFromEntityList(request as List<GithubUserResponse>)

        }.get<List<GithubUser>, List<GithubUserResponse>>() as Flow<Resource<List<GithubUser>>>
    }

    override suspend fun getUsersFromLocal(since: Int): Flow<Resource<List<GithubUser>>> {
        return object : SafeCacheStrategyHandler<List<GithubUser>, List<GithubUserResponse>>() {

            override suspend fun fetchRemoteData(): Response<List<GithubUserResponse>> =
                apiService.getUsers(since)

            override fun mapRequestToResult(request: Any): List<GithubUser> =
                mapper.mapFromEntityList(request as List<GithubUserResponse>)

            override suspend fun saveRemoteData(result: List<GithubUser>) =
                dao.insertUsers(result)

            override fun fetchLocalData(): Flow<List<GithubUser>> = dao.getUsers()

        }.get<List<GithubUser>, List<GithubUserResponse>>()
    }

}




