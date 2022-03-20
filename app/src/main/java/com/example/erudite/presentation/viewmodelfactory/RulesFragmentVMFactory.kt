package com.example.erudite.presentation.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.erudite.domain.repository.UserRepository
import com.example.erudite.presentation.viewmodel.RulesFragmentViewModel
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class RulesFragmentVMFactory @Inject constructor(val repository: UserRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        // return QuestionFragmentViewModel (context) as T
        return RulesFragmentViewModel (repository) as T
    }
}