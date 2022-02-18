package com.example.erudite.DI

import android.app.Application
import com.example.erudite.DI.components.AppComponent
import com.example.erudite.DI.components.DaggerAppComponent

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