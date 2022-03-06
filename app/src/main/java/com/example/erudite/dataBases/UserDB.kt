package com.example.erudite.dataBases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.erudite.model.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDB: RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object{
        @Volatile
        private var USERINSTANCE: UserDB? = null

        fun getDatabase(context: Context): UserDB {
            val tempInstance = USERINSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDB::class.java,
                    "user_db"
                ).build()
                USERINSTANCE = instance
                return instance
            }
        }
    }
}