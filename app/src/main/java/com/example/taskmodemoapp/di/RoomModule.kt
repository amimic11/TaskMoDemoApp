package com.example.taskmodemoapp.di

import android.content.Context
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import com.example.taskmodemoapp.room.AppDao
import com.example.taskmodemoapp.room.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent :: class)
object RoomModule {

    @Singleton
    @Provides
    fun provideAppDb(@ApplicationContext context: Context) : AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase :: class.java,
            AppDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideAppDao(appdatabase : AppDatabase) : AppDao {
        return appdatabase.appDao()
    }
}