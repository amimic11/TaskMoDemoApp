package com.example.taskmodemoapp.repository

import com.example.taskmodemoapp.model.Comment
import com.example.taskmodemoapp.model.Post
import com.example.taskmodemoapp.retrofit.AppRetrofit
import com.example.taskmodemoapp.retrofit.CommentNetworkMapper
import com.example.taskmodemoapp.retrofit.PostNetworkMapper
import com.example.taskmodemoapp.room.AppDao
import com.example.taskmodemoapp.room.CommentCacheMapper
import com.example.taskmodemoapp.room.PostCacheMapper
import com.example.taskmodemoapp.util.DataState
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class MainRepository(
    private val appDao: AppDao,
    private val appRetrofit: AppRetrofit,
    private val cacheMapper: PostCacheMapper,
    private val postNetworkMapper: PostNetworkMapper,
    private val commentCacheMapper: CommentCacheMapper,
    private val commentNetworkMapper: CommentNetworkMapper
){
    suspend fun getPosts() = flow<DataState<List<Post>>> {
        emit(DataState.Loading)
        try {
            val networkPost = appRetrofit.getPost()
            val posts = postNetworkMapper.mapFromEntityList(networkPost)
            for (post in posts) {
                appDao.insertPost(cacheMapper.mapToEnitity(post))
            }
            val cachePost = appDao.getPosts()
            emit(DataState.Success(cacheMapper.mapFromEntityList(cachePost)))
        } catch (e : Exception) {
            when{
                appDao.getPosts().isNotEmpty() -> {
                    val cachePost = appDao.getPosts()
                    emit(DataState.Success(cacheMapper.mapFromEntityList(cachePost)))
                }
                else -> emit(DataState.Error(e))
            }
//            emit(DataState.Error(e))/
        }
    }

    suspend fun getComments() = flow<DataState<List<Comment>>> {
        emit(DataState.Loading)
        try {
            val networkComment = appRetrofit.getComment()
            val comments = commentNetworkMapper.mapFromEntityList(networkComment)
            for (comment in comments) {
                appDao.insertComments(commentCacheMapper.mapToEnitity(comment))
            }
            val cacheComment = appDao.getComments()
            emit(DataState.Success(commentCacheMapper.mapFromEnityList(cacheComment)))
        } catch (e : Exception) {
            emit(DataState.Error(e))
        }
    }
}