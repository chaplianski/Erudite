package com.example.erudite.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.erudite.data.model.User
import com.example.erudite.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class AnswerFragmentViewModel @Inject constructor(uRepository: UserRepository): ViewModel() {

    val userRepository = uRepository

    fun updateAddTime (user: User){
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.updateUser(user)
        }
    }
}