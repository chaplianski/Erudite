package com.example.erudite.Repository

import android.content.Context
import com.example.erudite.DataBases.UserDB
import com.example.erudite.Models.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserRepository (context: Context){

    val userDataBase = UserDB.getDatabase(context)

    fun insertUser (user: User) {
       userDataBase.userDao().insertUser(user)
    }

    fun checkUser (nickname: String): Boolean{
       return userDataBase.userDao().checkBeingUser(nickname)
    }
}