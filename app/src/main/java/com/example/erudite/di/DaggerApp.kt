package com.example.erudite.di

import android.app.Application
import com.example.erudite.di.components.AppComponent
import com.example.erudite.di.components.DaggerAppComponent

class DaggerApp: Application() {

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .builder()
            .context(context = this)
            .build()
    }
}