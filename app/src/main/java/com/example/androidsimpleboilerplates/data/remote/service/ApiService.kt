package com.example.androidsimpleboilerplates.data.remote.service

import com.example.androidsimpleboilerplates.data.remote.dto.PicInfoDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("id/{id}/info")
    suspend fun getPicInfo(@Path("id") id: Int = 1): Response<PicInfoDto>
}