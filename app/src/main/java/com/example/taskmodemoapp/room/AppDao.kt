package com.example.taskmodemoapp.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AppDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPost(postEntity : PostCacheEntity) : Long

    @Query("SELECT * FROM posts")
    suspend fun getPosts() : List<PostCacheEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertComments(commentEntity : CommentCacheEntity) : Long

    @Query("SELECT * FROM comments")
    suspend fun getComments() : List<CommentCacheEntity>
}