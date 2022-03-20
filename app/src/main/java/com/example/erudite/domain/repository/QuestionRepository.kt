package com.example.erudite.domain.repository

import android.content.Context
import com.example.erudite.data.database.QuestionsDB
import com.example.erudite.data.model.Questions
import javax.inject.Inject

class QuestionRepository @Inject constructor (context: Context){

    val questionDatabase = QuestionsDB.getDatabase(context)
    val questionList  = listOf<Questions>( Questions(1,  "Ученый Дэвид Льюис из Англии, проведя исследование, выяснил, " +
            "что это безопасно только для женщин, в то время как для мужчин чревато появлением серьезных болезней. " +
            "В ходе эксперимента было обнаружено, что только у четверти женщин наблюдались какие-то незначительные " +
            "изменения в организме, к примеру, учащенное сердцебиение. Мужчины же воспринимали это крайне негативно " +
            "(учащение пульса, аритмия, высокое артериальное давление). Это пришло в русский язык из английского относительно недавно и так и закрепилось. Назовите это.", "Шопинг"),
        Questions(2,"Датчане любят говорить, что у инх все лучше, чем в Швеции: климат, природа, народ, история. Но только одно у шведов лучше. Что именно?", "Соседи",),
        Questions(3,"«Первый человек спустился на землю с неба», – гласит легенда африканского народа суахили. А кто ему в этом помог?", "Жираф"),
        Questions(4, "Дважды человеку дается это бесплатно. Если у него возникает желание получить это в третий раз, он должен заплатить. О чем идёт речь?", "Зубы"),
        Questions(5,"Кого изобразили на плакатах против расизма, потому что они и белые, и чёрные, и азиаты?", "Панда" ),
        Questions(6, "Она расскажет вам о любви ценой собственной жизни.", "Ромашка")
    )


        fun addQuestion(){
            for ( i in questionList.indices){
                questionDatabase.QuestionDao().insertQuestion(questionList[i])
            }
        }

        fun getSizeQuestionDB (): Int{
            return questionDatabase.QuestionDao().getCountQuestion()
        }

        fun getQuestion(id: Int): Questions {
            return questionDatabase.QuestionDao().getBodyQuestion(id)
        }

        fun deleteAllQuestions(){
            questionDatabase.QuestionDao().deleteAll()
        }

        fun getAllQuestions(): List<Questions>{
            return questionDatabase.QuestionDao().selectAll()
        }
}

