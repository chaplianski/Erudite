package com.example.erudite.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.erudite.model.Questions
import com.example.erudite.repository.QuestionRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


//class QuestionFragmentViewModel (application: Application): AndroidViewModel(application){

//class QuestionFragmentViewModel @Inject constructor(context: Context): ViewModel(){
    class QuestionFragmentViewModel @Inject constructor(repository: QuestionRepository): ViewModel(){

   // val questionRepository = QuestionRepository(context)
    val questionRepository = repository
    val questions = MutableLiveData<Questions>()

    init {
       CoroutineScope(Dispatchers.IO).launch {


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
/*
    fun addQuestion(){
        viewModelScope.launch(Dispatchers.IO){
            questionRepository.addQuestion()
        }

    }*/

    fun getQuestion(id: Int): Questions{
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