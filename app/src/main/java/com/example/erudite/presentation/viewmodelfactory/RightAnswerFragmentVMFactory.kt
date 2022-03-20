package com.example.erudite.presentation.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.erudite.domain.repository.UserRepository
import com.example.erudite.presentation.viewmodel.RightanswerFragmentViewModel
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class RightAnswerFragmentVMFactory @Inject constructor(val repository: UserRepository): ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return RightanswerFragmentViewModel (repository) as T
        }
    }