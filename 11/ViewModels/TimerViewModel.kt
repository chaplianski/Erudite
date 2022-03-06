package com.example.erudite.ViewModels

import android.app.Application
import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import kotlin.concurrent.schedule

class TimerViewModel(application: Application, val timerCount: Long, val delay: Long): AndroidViewModel(application) {

    val timerLiveData = MutableLiveData<String>()

    init {
            Log.d("MyLog", "delay in vm = $delay")
            Timer().schedule(delay/1000){
                CoroutineScope(Dispatchers.Main).launch {
                    startTimer()
                }
            }
         }

    fun startTimer(){

        object : CountDownTimer (timerCount, 1000){
            override fun onTick(p0: Long) {
                timerLiveData.value = (p0/1000).toString()
            }

            override fun onFinish() {

            }

        }.start()
    }

}