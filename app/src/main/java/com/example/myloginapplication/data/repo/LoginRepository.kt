package com.example.myloginapplication.data.repo

import com.example.myloginapplication.data.db.LoginDao
import com.example.myloginapplication.data.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class LoginRepository @Inject constructor(val dao: LoginDao):CoroutineScope {

//    fun getLogins(): Flow<List<Login>> {
//        return flow<List<Login>> {
//            dao.getLogins()
//        }.flowOn(context = Dispatchers.IO)
//    }
//
//    fun getLogin(email:String): Flow<List<Login>> {
//        return flow<List<Login>> {
//            dao.getLogin(email)
//        }.flowOn(context = Dispatchers.IO)
//
//    }

   suspend fun getUser(): List<User> {
//        return flow<List<User>> {
//            dao.getUser()
//        }.flowOn(context = Dispatchers.IO)
return async(context = Dispatchers.IO) { dao.getUser() }.await()
    }

//    fun insertLogin(login: Login){
//        launch {
//            dao.insertLogin(login)
//        }
//    }

    fun insertUser(user: User){
        launch {
            dao.insertUser(user)
        }
    }

 fun deleteUser(user: User){
        launch {
            dao.deleteUser(user)
        }
    }


    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO
}