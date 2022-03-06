package com.example.erudite.dataBases

import androidx.room.*
import com.example.erudite.model.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUser (user: User)

    @Update
    fun updateUser(user: User)

    @Query("DELETE FROM users WHERE userId= :id")
    fun deleteUser (id: Int)

    //@Query ("SELECT COUNT(questionId) FROM userAnswer WHERE isRightAnswer= :result")
//    fun  qualityRightAnswer (result: Boolean): Int

    @Query("SELECT * FROM users WHERE nickname= :nick")
    fun getUser (nick: String): User

    @Query("SELECT * FROM users")
    fun loadUsers(): List<User>
}