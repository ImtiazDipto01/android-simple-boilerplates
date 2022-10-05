package com.example.androidsimpleboilerplates.data.remote.service

import com.example.androidsimpleboilerplates.data.remote.dto.GithubUserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("users")
    suspend fun getUsers(@Query("id") since: Int = 0): Response<List<GithubUserResponse>>
}