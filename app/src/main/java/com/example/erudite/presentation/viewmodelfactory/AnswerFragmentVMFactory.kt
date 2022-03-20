package com.example.erudite.presentation.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.erudite.domain.repository.UserRepository
import com.example.erudite.presentation.viewmodel.AnswerFragmentViewModel
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class AnswerFragmentVMFactory @Inject constructor(val uRepository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AnswerFragmentViewModel (uRepository) as T
    }
}