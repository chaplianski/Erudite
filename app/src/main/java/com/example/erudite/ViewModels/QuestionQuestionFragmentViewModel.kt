package com.example.erudite.ViewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.erudite.Models.Questions
import com.example.erudite.Repository.QuestionRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.random.Random

class QuestionQuestionFragmentViewModel(application: Application): AndroidViewModel(application) {

    val questions = MutableLiveData<Questions>()
    val questionRepository = QuestionRepository(application)
 //   val questionList = emptyList<>()

    init {
       CoroutineScope(Dispatchers.IO).launch {
           deleteAllQuestions()
           addQuestion()
           val id = chooseQuestion()
           questions.postValue(getQuestion(id))
    //      getAll()
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

    fun addQuestion(){
        questionRepository.addQuestion()
    }

    fun getQuestion(id: Int): Questions{
        return questionRepository.getQuestion(id)
    }

    fun deleteAllQuestions (){
        questionRepository.deleteAllQuestions()
    }
/*
    fun getAll (){
        val r = questionRepository.getAllQuestions()
        Log.d("MyLog", "All Question = ${r.toString()}")
    }

 */
}