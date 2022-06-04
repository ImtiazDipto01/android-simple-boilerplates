package com.example.androidsimpleboilerplates.data.remote.repository

import com.example.androidsimpleboilerplates.core.base.BaseRepository
import com.example.androidsimpleboilerplates.core.extensions.Resource
import com.example.androidsimpleboilerplates.data.remote.dto.toPicInfo
import com.example.androidsimpleboilerplates.data.remote.service.ApiService
import com.example.androidsimpleboilerplates.domain.model.PicInfo
import com.example.androidsimpleboilerplates.domain.repository.PicInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import javax.inject.Inject

class PicInfoRepositoryImp @Inject constructor(private val apiService: ApiService) :
    BaseRepository(), PicInfoRepository {

    override suspend fun getPicInfo(): Flow<Resource<PicInfo>> = safeApiCall {
        apiService.getPicInfo()
    }.transform {
        if (it is Resource.Success)
            emit(Resource.Success(it.data.toPicInfo()))
        else emit(it as Resource<PicInfo>)
    }

}