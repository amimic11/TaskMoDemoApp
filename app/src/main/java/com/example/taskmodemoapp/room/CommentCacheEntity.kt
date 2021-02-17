package com.example.taskmodemoapp.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comments")
class CommentCacheEntity (

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id : Int,

    @ColumnInfo(name = "postId")
    var postId : Int,

    @ColumnInfo(name = "name")
    var name : String,

    @ColumnInfo(name = "email")
    var email : String,

    @ColumnInfo(name = "body")
    var body : String
)