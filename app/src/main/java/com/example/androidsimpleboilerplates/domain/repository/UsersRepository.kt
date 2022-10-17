package com.example.androidsimpleboilerplates.domain.repository

import com.example.androidsimpleboilerplates.core.extensions.Resource
import com.example.androidsimpleboilerplates.data.local.db.entity.GithubUser
import kotlinx.coroutines.flow.Flow

interface UsersRepository {
    suspend fun getUsers(since: Int): Flow<Resource<List<GithubUser>>>

    suspend fun getUsersFromLocal(since: Int): Flow<Resource<List<GithubUser>>>
}