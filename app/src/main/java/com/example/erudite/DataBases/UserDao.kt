package com.example.erudite.DataBases

import androidx.room.*
import com.example.erudite.model.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUser (user: User)

    @Query ("DELETE FROM users WHERE userId= :id")
    fun deleteUser (id: Int)

 //   @Query ("SELECT COUNT(questionId) FROM userAnswer WHERE isRightAnswer= :result")
//    fun  qualityRightAnswer (result: Boolean): Int

    @Query ("SELECT * FROM users WHERE nickname= :nick")
    fun checkBeingUser (nick: String): Boolean

    @Query ("SELECT * FROM users")
    fun loadUsers(): List<User>
}