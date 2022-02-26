package com.example.erudite.ui

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.erudite.model.Questions
import com.example.erudite.repository.QuestionRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


//class QuestionFragmentViewModel (application: Application): AndroidViewModel(application){

class QuestionFragmentViewModel @Inject constructor(context: Context): ViewModel(){


    val questionRepository = QuestionRepository(context)
    val questions = MutableLiveData<Questions>()

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