package com.example.erudite.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.erudite.model.User
import com.example.erudite.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class RulesFragmentViewModel @Inject constructor(repository: UserRepository): ViewModel() {

    val currentUser = MutableLiveData<User>()
    val userRepository = repository

    fun getUser (user: String){
           viewModelScope.launch(Dispatchers.IO) {
            currentUser.postValue(userRepository.getUser(user))
        }
    }

    fun updateUser(user: User){

        viewModelScope.launch(Dispatchers.IO) {
            userRepository.updateUser(user)


        }
    }


}