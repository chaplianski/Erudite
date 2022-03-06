package com.example.erudite.dataBases

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.erudite.model.Questions

@Dao
interface QuestionDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertQuestion(question: Questions)

    @Query("DELETE FROM questions")
    fun deleteAll ()

    @Query("SELECT * FROM questions")
    fun selectAll(): List<Questions>

    @Query("SELECT COUNT(questionId) FROM questions")
    fun getCountQuestion(): Int

    @Query("SELECT questionId, question, answer FROM questions WHERE questionId= :id")
    fun getBodyQuestion(id: Int): Questions
}