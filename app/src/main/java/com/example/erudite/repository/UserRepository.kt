package com.example.erudite.repository


import android.content.Context
import com.example.erudite.DataBases.UserDB
import com.example.erudite.model.User


class UserRepository (context: Context){



    val userDataBase = UserDB.getDatabase(context)//UserDB.getDatabase(context)

    fun insertUser (user: User) {
       userDataBase.userDao().insertUser(user)
    }

    fun checkUser (nickname: String): Boolean{
       return userDataBase.userDao().checkBeingUser(nickname)
    }
}