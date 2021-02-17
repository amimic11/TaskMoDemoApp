package com.example.taskmodemoapp.di

import com.example.taskmodemoapp.repository.MainRepository
import com.example.taskmodemoapp.retrofit.AppRetrofit
import com.example.taskmodemoapp.retrofit.CommentNetworkMapper
import com.example.taskmodemoapp.retrofit.PostNetworkMapper
import com.example.taskmodemoapp.room.AppDao
import com.example.taskmodemoapp.room.CommentCacheMapper
import com.example.taskmodemoapp.room.PostCacheMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent :: class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(
        appDao : AppDao,
        retrofit : AppRetrofit,
        cacheMapper : PostCacheMapper,
        networkMapper : PostNetworkMapper,
        commentCacheMapper : CommentCacheMapper,
        commentNetworkMapper : CommentNetworkMapper
    ) : MainRepository {
        return MainRepository(appDao, retrofit, cacheMapper, networkMapper, commentCacheMapper, commentNetworkMapper)
    }
}