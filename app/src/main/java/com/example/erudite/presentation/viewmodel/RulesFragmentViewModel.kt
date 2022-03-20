package com.example.erudite.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.erudite.data.model.User
import com.example.erudite.domain.repository.UserRepository
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