package com.example.erudite.DI.components

import android.app.Application
import android.content.Context
import com.example.erudite.DI.DaggerApp
import com.example.erudite.DI.modules.AppModule
import com.example.erudite.ui.QuestionFragment
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton


@Component(modules = [AppModule::class])
interface AppComponent {

 //     val application: DaggerApp

//    fun AppC(): Application{
 //       return application
//    }
    fun inject(questionFragment: QuestionFragment)

 //   @Component.Factory
  //  annotation class QuestionFactory: AndroidInjector.Factory<A>

    @Component.Builder
    interface Builder{

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent


    }
}


