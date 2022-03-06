package com.example.erudite.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.erudite.repository.UserRepository
import com.example.erudite.ui.RightanswerFragmentViewModel
import com.example.erudite.ui.RulesFragmentViewModel
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class RightAnswerFragmentVMFactory @Inject constructor(val repository: UserRepository): ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return RightanswerFragmentViewModel (repository) as T
        }
    }