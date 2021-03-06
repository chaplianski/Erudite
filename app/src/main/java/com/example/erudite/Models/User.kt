package com.example.erudite.Models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "users")
data class User (
    @PrimaryKey (autoGenerate = true)
    @ColumnInfo (name = "userId")
    val userId: Int = 0,
    @ColumnInfo(name = "nickname")
    val nickname: String,
    @ColumnInfo(name = "score")
    val score: Int = 0,
    @ColumnInfo(name = "additionTime")
    val addionTime: Long = 0L,
)


