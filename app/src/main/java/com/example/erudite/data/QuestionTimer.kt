package com.example.erudite.data

import android.util.Log
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

class QuestionTimer (questionDuration: Int, pauseBeforQoestion: Int) {

    val duration = questionDuration
    val pause = (pauseBeforQoestion * 40).toLong()


    val timerValue = flow<Int> {
        delay(pause)
        Log.d("MyLog", "Pause = $pause")
        for (i in duration downTo 0){
            emit(i)
            delay(1000L)
        }
    }





}