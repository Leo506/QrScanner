package com.example.qrscanner

import android.app.Application
import infrastructure.EncodingRequestsRepository
import infrastructure.EncodingRequestsRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

class MainApp : Application() {
    override fun onCreate() {
        super.onCreate()

        val appModule = module {
            singleOf(::EncodingRequestsRepositoryImpl) { bind<EncodingRequestsRepository>() }
        }

        startKoin {
            androidLogger()
            androidContext(this@MainApp)
            modules(appModule)
        }
    }
}