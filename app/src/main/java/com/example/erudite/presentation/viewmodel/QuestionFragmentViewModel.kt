package com.example.erudite.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.erudite.data.QuestionTimer
import com.example.erudite.data.model.Questions
import com.example.erudite.domain.repository.QuestionRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.lang.reflect.Array.get
import java.util.concurrent.Flow
import javax.inject.Inject


class QuestionFragmentViewModel @Inject constructor(repository: QuestionRepository): ViewModel(){

    val questionRepository = repository
    private val _questions = MutableLiveData<Questions>()
    val questions: LiveData<Questions> get()= _questions

    init {
       CoroutineScope(Dispatchers.IO).launch {

           val id = chooseQuestion()
           _questions.postValue(getQuestion(id))

           Log.d("MyLog", "Question = ${getQuestion(id)}")
       }

    }


    fun chooseQuestion (): Int {
        val sizeDb = questionRepository.getSizeQuestionDB()
        Log.d("MyLog", "Size DB = $sizeDb")
        val idQuestion = (1..sizeDb).random()
        Log.d("MyLog", "IdQuestion = $idQuestion")
        return idQuestion
    }

/*
    private val _timerValue = MutableStateFlow(getTimer())
    val timerValue: StateFlow<Long> = _timerValue.asStateFlow()

    fun getTimer (): StateFlow<Long> {
        return questionTimer.timerValue

    }*/

    fun getQuestion(id: Int): Questions {
        return questionRepository.getQuestion(id)
    }

    fun deleteAllQuestions (){
       viewModelScope.launch(Dispatchers.IO) {
           questionRepository.deleteAllQuestions()
       }
    }


/*
    fun getAll (){
        val r = questionRepository.getAllQuestions()
        Log.d("MyLog", "All Question = ${r.toString()}")
    }

 */
}