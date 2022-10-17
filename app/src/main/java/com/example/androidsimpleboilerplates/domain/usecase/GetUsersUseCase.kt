package com.example.androidsimpleboilerplates.domain.usecase

import com.example.androidsimpleboilerplates.core.extensions.Resource
import com.example.androidsimpleboilerplates.data.local.db.entity.GithubUser
import com.example.androidsimpleboilerplates.domain.repository.UsersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val repository: UsersRepository
) {
    suspend fun execute(since: Int): Flow<Resource<List<GithubUser>>> =
        repository.getUsers(since)
}