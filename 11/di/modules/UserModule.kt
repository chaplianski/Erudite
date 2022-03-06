package com.example.erudite.di.modules

import android.content.Context
import androidx.room.Room
import com.example.erudite.data.databases.UserDB
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UserModule {

    @Singleton
    @Provides
    fun provideUserDao(userDB: UserDB) = userDB.userDao()

    @Singleton
    @Provides
    fun provideQuestionDB(context: Context): UserDB =
        Room.databaseBuilder(
            context,
            UserDB::class.java,
            "user_db"
        ).build()
}