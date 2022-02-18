package com.example.erudite.DI.modules

import android.content.Context
import androidx.room.Room
import com.example.erudite.DataBases.QuestionsDB
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule() {


 //   @Singleton
 //   @Provides
 //   fun appContext (context: Context) = context

    @Singleton
    @Provides
    fun provideQuestionDao(questionDB: QuestionsDB) = questionDB.QuestionDao()

    @Singleton
    @Provides
    fun provideQuestionDB(context: Context): QuestionsDB =
        Room.databaseBuilder(
            context,
            QuestionsDB::class.java,
            "question_db"
        ).build()

}
