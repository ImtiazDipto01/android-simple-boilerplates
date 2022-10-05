package com.example.androidsimpleboilerplates.domain.model

import com.google.gson.annotations.SerializedName

data class GithubUser(
    val login: String? = null,
    val avatarUrl: String? = null,
    val nodeId: String? = null,
    val url: String? = null,
)
