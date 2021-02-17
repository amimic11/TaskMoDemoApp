package com.example.taskmodemoapp.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


@Entity(tableName = "posts")
data class PostCacheEntity(

    @SerializedName("userId")
    @Expose
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id : Int,
    @ColumnInfo(name = "userId")
    var userId : Int,
    @ColumnInfo(name = "title")
    var title : String,
    @ColumnInfo(name = "body")
    var body : String
)