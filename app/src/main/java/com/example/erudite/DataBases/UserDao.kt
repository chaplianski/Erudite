package com.example.erudite.DataBases

import androidx.room.*
import com.example.erudite.Models.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUser (user: User)

    @Query ("DELETE FROM users")
    fun deleteALL ()

 //   @Query ("SELECT COUNT(questionId) FROM userAnswer WHERE isRightAnswer= :result")
//    fun  qualityRightAnswer (result: Boolean): Int

    @Query ("SELECT * FROM users WHERE nickname= :nick")
    fun checkBeingUser (nick: String): Boolean
}