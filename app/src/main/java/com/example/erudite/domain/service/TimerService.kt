package com.example.erudite.domain.service

import android.content.Context
import android.os.CountDownTimer
import androidx.work.Worker
import androidx.work.WorkerParameters
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import kotlin.concurrent.schedule

/*
class TimerService (appContext: Context, params: WorkerParameters): Worker(appContext, params){

    override fun doWork(): Result {
        return Result.success()
    }
    //(pause: Long, duration: Long): Worker() {

 //   val pauseBefor = pause
 //   val timerDuration = duration
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


}*/