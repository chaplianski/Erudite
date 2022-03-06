package com.example.erudite.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "questions")
data class Questions (
    @PrimaryKey (autoGenerate = true)
    @ColumnInfo (name = "questionId")
    val id: Int = 0,
    @ColumnInfo(name = "question")
    val question: String,
    @ColumnInfo(name = "answer")
    val answer: String,
)

