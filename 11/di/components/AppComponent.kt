package com.example.erudite.di.components

import android.content.Context
import com.example.erudite.di.modules.AppModule
import com.example.erudite.di.modules.UserModule
import com.example.erudite.ui.QuestionFragment
import com.example.erudite.ui.RightAnswerFragment
import com.example.erudite.ui.RulesFragment
import dagger.BindsInstance
import dagger.Component


@Component(modules = [AppModule::class, UserModule::class])
interface AppComponent {

    fun questionFragmentInject(questionFragment: QuestionFragment)
    fun rulesFragmentInject(rulesFragment: RulesFragment)
    fun rightAnswerFragmentInject(rightAnswerFragment: RightAnswerFragment)



    @Component.Builder
    interface Builder{

        @BindsInstance
        fun context(context: Context): Builder
        fun build(): AppComponent
    }
}


