package com.example.erudite.DI

import android.app.Application

class MainApp: Application() {

    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
    //    appComponent = DaggerA
    }
}