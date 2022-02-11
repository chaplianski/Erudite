package com.example.erudite.DI

import com.example.erudite.Models.Questions
import dagger.Component
import dagger.Module
import dagger.Provides

@Component
interface AppComponent {
}

@Module
object AppModule {

    @Provides
    fun provideDBQuestion(
        questions: Questions
    ):Questions{
        return questions

    }
}