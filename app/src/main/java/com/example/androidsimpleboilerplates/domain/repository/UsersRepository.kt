package com.example.androidsimpleboilerplates.domain.repository

import com.example.androidsimpleboilerplates.core.extensions.Resource
import com.example.androidsimpleboilerplates.data.remote.dto.GithubUserResponse
import kotlinx.coroutines.flow.Flow

interface UsersRepository {
    suspend fun getPicInfo(): Flow<Resource<List<GithubUserResponse>>>
}