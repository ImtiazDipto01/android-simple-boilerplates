package com.example.androidsimpleboilerplates.domain.repository

import com.example.androidsimpleboilerplates.core.extensions.Resource
import com.example.androidsimpleboilerplates.domain.model.PicInfo
import kotlinx.coroutines.flow.Flow

interface PicInfoRepository {
    suspend fun getPicInfo(): Flow<Resource<PicInfo>>
}