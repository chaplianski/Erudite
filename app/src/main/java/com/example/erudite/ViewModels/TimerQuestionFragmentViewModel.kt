package com.example.erudite.ViewModels

import android.os.CountDownTimer
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import kotlin.concurrent.schedule

class TimerQuestionFragmentViewModel: ViewModel() {

    val timerLiveData = MutableLiveData<String>()

    init {

            Timer().schedule(2000){
                CoroutineScope(Dispatchers.Main).launch {
                    startTimer()
                }
            }
         }

    fun startTimer(){

        object : CountDownTimer (60000, 1000){
            override fun onTick(p0: Long) {
                timerLiveData.value = (p0/1000).toString()
            }

            override fun onFinish() {

            }

        }.start()
    }

}