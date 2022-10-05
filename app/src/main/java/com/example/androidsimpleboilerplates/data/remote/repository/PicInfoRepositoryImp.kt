package com.example.androidsimpleboilerplates.data.remote.repository

import com.example.androidsimpleboilerplates.core.extensions.Resource
import com.example.androidsimpleboilerplates.core.extensions.safeApiCall
import com.example.androidsimpleboilerplates.data.remote.dto.GithubUserResponse
import com.example.androidsimpleboilerplates.data.remote.service.ApiService
import com.example.androidsimpleboilerplates.domain.repository.PicInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import javax.inject.Inject

class PicInfoRepositoryImp @Inject constructor(private val apiService: ApiService) :
    PicInfoRepository {

    override suspend fun getPicInfo(): Flow<Resource<List<GithubUserResponse>>> = safeApiCall {
        apiService.getPicInfo()
    }

}