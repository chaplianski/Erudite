package com.example.erudite.presentation.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.erudite.domain.repository.QuestionRepository
import com.example.erudite.presentation.viewmodel.QuestionFragmentViewModel
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class QuestionFragmentVMFactory @Inject constructor(val repository: QuestionRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
       // return QuestionFragmentViewModel (context) as T
        return QuestionFragmentViewModel (repository) as T
    }
}