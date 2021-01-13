package com.example.myloginapplication.ui.viewmodel

import androidx.lifecycle.*
import com.example.myloginapplication.data.model.User
import com.example.myloginapplication.data.repo.LoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(val repository: LoginRepository):ViewModel() {

//    var logins = liveData<List<Login>> {
//        repository.getLogins().asLiveData(context = Dispatchers.Main)
//    }
//    fun getLogin(email:String): LiveData<List<Login>> {
//       return repository.getLogin(email).asLiveData(context = Dispatchers.Main)
//    }
//
//    fun insertLogin(login: Login){
//        viewModelScope.launch (context = Dispatchers.Main){
//            repository.insertLogin(login)
//        }
//    }

//    var users= liveData<List<User>> {
//         repository.getUser().asLiveData(context = Dispatchers.Main)
//
//    }

    internal val users: MutableLiveData<List<User>> by lazy{MutableLiveData()}

    fun getUser() {
      viewModelScope.launch (context = Dispatchers.Main){
          users.value=repository.getUser()
      }
    }

    fun insertUser(user: User){
        viewModelScope.launch(context = Dispatchers.Main) {
            repository.insertUser(user)
            getUser()
        }
    }
    fun deleteUser(user: User){
        viewModelScope.launch(context = Dispatchers.Main) {
            repository.deleteUser(user)
            getUser()
        }
    }

}