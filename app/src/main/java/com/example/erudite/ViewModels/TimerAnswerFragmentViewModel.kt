package com.example.erudite.ViewModels

import android.os.CountDownTimer
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TimerAnswerFragmentViewModel: ViewModel() {

    val timerAnswer = MutableLiveData<String>()

    init {
           answerTimer()

    }

    fun answerTimer(){
        object : CountDownTimer (20000, 1000){
            override fun onTick(p0: Long) {
                timerAnswer.value = (p0/1000).toString()
            }

            override fun onFinish() {

            }

        }.start()
    }
}