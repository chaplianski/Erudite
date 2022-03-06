package com.example.erudite

import android.os.CountDownTimer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import kotlin.concurrent.schedule

class TimerService (pause: Long, duration: Long) {

    val pauseBefor = pause
    val timerDuration = duration
    var timeRest = ""

    init {

        Timer().schedule(pauseBefor/1000){
            CoroutineScope(Dispatchers.Main).launch {
                startTimer()
            }
        }
    }

    fun startTimer(){

        object : CountDownTimer(timerDuration, 1000){
            override fun onTick(p0: Long) {
                timeRest = (p0/1000).toString()
            }

            override fun onFinish() {

            }

        }.start()
    }
}