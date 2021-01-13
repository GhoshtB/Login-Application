package com.example.myloginapplication.data.db

import androidx.room.*
import com.example.myloginapplication.data.model.User

@Dao
interface LoginDao {

//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//   suspend fun insertLogin(login: Login)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertUser(user: User)

//    @Query("SELECT * FROM login WHERE  email =:mail")
//   suspend fun getLogin(mail:String):List<Login>

//   @Query("Select * FROM login")
//   suspend fun getLogins():List<Login>

   @Query("SELECT * FROM user")
   suspend fun getUser():List<User>

   @Update
   suspend fun updateUser(user: User)

   @Delete
   suspend fun deleteUser(user: User)
}