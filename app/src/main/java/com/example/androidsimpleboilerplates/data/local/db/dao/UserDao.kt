package com.example.androidsimpleboilerplates.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.androidsimpleboilerplates.data.local.db.entity.GithubUser
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: GithubUser)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(user: List<GithubUser>)

    @Query("select * from user where id = :id")
    suspend fun getUser(id: Int): GithubUser?

    @Query("select * from user")
    fun getUsers(): Flow<List<GithubUser>>
}