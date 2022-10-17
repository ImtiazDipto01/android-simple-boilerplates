package com.example.androidsimpleboilerplates.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.androidsimpleboilerplates.core.extensions.APP_DB
import com.example.androidsimpleboilerplates.data.local.db.dao.UserDao
import com.example.androidsimpleboilerplates.data.local.db.entity.GithubUser

@Database(
    entities = [GithubUser::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        const val DATABASE_NAME: String = APP_DB
    }
}