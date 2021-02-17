package com.example.taskmodemoapp.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PostCacheEntity :: class, CommentCacheEntity :: class], version = 1)

abstract class AppDatabase : RoomDatabase() {

    abstract fun appDao() : AppDao

    companion object {
        val DATABASE_NAME : String = "app_database"
    }
}