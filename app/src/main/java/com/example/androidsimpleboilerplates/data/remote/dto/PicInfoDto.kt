package com.example.androidsimpleboilerplates.data.remote.dto

import com.example.androidsimpleboilerplates.domain.model.PicInfo
import com.google.gson.annotations.SerializedName

data class PicInfoDto(

    @field:SerializedName("author")
    val author: String? = null,

    @field:SerializedName("width")
    val width: Int? = null,

    @field:SerializedName("download_url")
    val downloadUrl: String? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("url")
    val url: String? = null,

    @field:SerializedName("height")
    val height: Int? = null
)

fun PicInfoDto.toPicInfo(): PicInfo = PicInfo(author, width, downloadUrl, id, url, height)