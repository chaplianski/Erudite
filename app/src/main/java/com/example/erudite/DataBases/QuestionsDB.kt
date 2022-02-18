package com.example.erudite.DataBases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.erudite.model.Questions

@Database(entities = [Questions::class],version = 1,exportSchema = false)
abstract class QuestionsDB: RoomDatabase() {
    abstract fun QuestionDao():QuestionDao

    companion object{
        @Volatile
        private var INSTANCE:QuestionsDB? = null

        fun getDatabase(context: Context): QuestionsDB {
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    QuestionsDB::class.java,
                    "question_db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}