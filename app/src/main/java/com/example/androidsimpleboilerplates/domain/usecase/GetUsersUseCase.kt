package com.example.androidsimpleboilerplates.domain.usecase

import com.example.androidsimpleboilerplates.core.extensions.Resource
import com.example.androidsimpleboilerplates.data.remote.dto.GithubUserResponse
import com.example.androidsimpleboilerplates.domain.model.GithubUser
import com.example.androidsimpleboilerplates.domain.repository.UsersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val repository: UsersRepository
) {
    suspend fun execute(): Flow<Resource<List<GithubUser>>> = repository.getUsers()
}