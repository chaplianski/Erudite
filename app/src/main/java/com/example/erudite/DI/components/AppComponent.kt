package com.example.erudite.DI.components

import android.app.Application
import android.content.Context
import com.example.erudite.DI.modules.AppModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Component(modules = [AppModule::class])
interface AppComponent {

 //  val application: Application

//    fun AppC(): Application{
 //       return application
//    }

//    val questionDao: QuestionDao
//    val questionDB: QuestionsDB
//    val questionRepository: QuestionRepository

    @Component.Builder
    interface Builder{

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }
}


