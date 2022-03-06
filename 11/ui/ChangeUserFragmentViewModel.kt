package com.example.erudite.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.erudite.UserRVAdapter
import com.example.erudite.model.User
import com.example.erudite.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChangeUserFragmentViewModel(application: Application): AndroidViewModel(application) {

    val userLiveData = MutableLiveData<List<User>>()
    val userRepository = UserRepository(application)


    init {


        CoroutineScope(Dispatchers.IO).launch {
            userLiveData.postValue(getUsersList())

            }
        }


    fun getUsersList(): List<User>{
        return userRepository.loadUsers().toList()
    }

    fun addUser(user: User) {
        CoroutineScope(Dispatchers.IO).launch {
            userRepository.insertUser(user)
        }
    }

    fun deleteUser(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            userRepository.deleteUser(id)


        }
    }


}