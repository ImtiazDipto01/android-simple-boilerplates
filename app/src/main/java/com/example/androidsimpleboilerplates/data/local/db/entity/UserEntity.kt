package com.example.androidsimpleboilerplates.data.local.db.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "id")
  @NonNull
  val id: Int = 1,

  @ColumnInfo(name = "uid")
  val uid: String

)