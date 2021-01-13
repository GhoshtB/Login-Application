package com.example.myloginapplication.di.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.myloginapplication.data.db.LoginDao
import com.example.myloginapplication.data.db.db.AppDatabase
import com.example.myloginapplication.data.repo.LoginRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun getDatabase(application: Application):AppDatabase{
        return Room.databaseBuilder(application,AppDatabase::class.java,AppDatabase.DB_NAME).build()
    }

    @Singleton
    @Provides
    fun provideDao(appDatabase: AppDatabase):LoginDao{
        return appDatabase.logindao()
    }

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application
    }

    @Singleton
    @Provides
    fun providesRepo(dao: LoginDao):LoginRepository{
        return LoginRepository(dao)
    }
}