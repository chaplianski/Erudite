package com.example.erudite.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.erudite.repository.QuestionRepository
import com.example.erudite.repository.UserRepository
import com.example.erudite.ui.QuestionFragmentViewModel
import com.example.erudite.ui.RulesFragmentViewModel
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class RulesFragmentVMFactory @Inject constructor(val repository: UserRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        // return QuestionFragmentViewModel (context) as T
        return RulesFragmentViewModel (repository) as T
    }
}