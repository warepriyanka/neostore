package com.example.neostoreapplication

import android.app.Application
import com.example.neostoreapplication.di.dbModule
import com.example.neostoreapplication.di.repositoryModule
import com.example.neostoreapplication.di.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CoreApplication:Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CoreApplication)
            modules(listOf(dbModule, repositoryModule,  uiModule))
        }
    }

}