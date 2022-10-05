package com.example.androidsimpleboilerplates.domain.usecase

import com.example.androidsimpleboilerplates.core.extensions.Resource
import com.example.androidsimpleboilerplates.data.remote.dto.GithubUserResponse
import com.example.androidsimpleboilerplates.domain.repository.PicInfoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPicInfoUseCase @Inject constructor(
    private val repository: PicInfoRepository
) {
    suspend fun execute(): Flow<Resource<List<GithubUserResponse>>> = repository.getPicInfo()
}