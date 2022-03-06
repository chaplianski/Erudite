package com.example.erudite.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.erudite.model.User
import com.example.erudite.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class RightanswerFragmentViewModel @Inject constructor (repository: UserRepository): ViewModel(){

    val userRepository = repository
    val currentUser = MutableLiveData<User>()

    fun getUser (nickname: String){
        viewModelScope.launch(Dispatchers.IO) {
            currentUser.postValue(userRepository.getUser(nickname))
        }

    }

    fun updateUser (user: User){
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.updateUser(user)
        }
    }
}