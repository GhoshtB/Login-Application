package com.example.myloginapplication.data.db.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myloginapplication.data.db.LoginDao
import com.example.myloginapplication.data.model.User

@Database(entities = arrayOf(User::class),version = 1,exportSchema = false)
abstract class AppDatabase():RoomDatabase() {

    companion object {
        const val DB_NAME = "login.db"
    }

    abstract fun logindao():LoginDao
}