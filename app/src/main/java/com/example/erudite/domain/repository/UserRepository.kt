package com.example.erudite.domain.repository

import android.content.Context
import com.example.erudite.data.database.UserDB
import com.example.erudite.data.model.User
import javax.inject.Inject


class UserRepository @Inject constructor(context: Context){


    val userDataBase = UserDB.getDatabase(context)//UserDB.getDatabase(context)

    fun insertUser (user: User) {
       userDataBase.userDao().insertUser(user)
    }

    fun updateUser(user: User){
        userDataBase.userDao().updateUser(user)
    }

    fun getUser (nickname: String): User {
       return userDataBase.userDao().getUser(nickname)
    }

    fun  loadUsers (): List<User>{
        return userDataBase.userDao().loadUsers()
    }

    fun deleteUser(id: Int) {
        userDataBase.userDao().deleteUser(id)
    }
}