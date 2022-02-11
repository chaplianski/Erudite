package com.example.erudite.ViewModels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class TimerViewModelFactory(private val application: Application, val timerCount: Long, val delay: Long): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TimerViewModel(application, timerCount, delay) as T
    }
}