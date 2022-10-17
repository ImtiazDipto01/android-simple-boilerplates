package com.example.androidsimpleboilerplates.data.local.db.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class GithubUser(
    @PrimaryKey
    @NonNull @ColumnInfo(name = "id") val id: Int,

    @NonNull @ColumnInfo(name = "login") val login: String,
    @ColumnInfo(name = "avatar_url") val avatarUrl: String? = null,
    @ColumnInfo(name = "url ") val url: String? = null,
)
