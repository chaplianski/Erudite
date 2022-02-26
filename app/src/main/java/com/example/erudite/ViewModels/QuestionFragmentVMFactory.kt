package com.example.erudite.ViewModels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.erudite.ui.QuestionFragmentViewModel
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class QuestionFragmentVMFactory @Inject constructor(val context: Context): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return QuestionFragmentViewModel (context) as T
    }
}