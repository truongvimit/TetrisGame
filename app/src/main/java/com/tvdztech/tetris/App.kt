package com.tvdztech.tetris

import android.app.Application
import com.tvdztech.tetris.di.dispatcherModule
import com.tvdztech.tetris.di.networkModule
import com.tvdztech.tetris.di.repositoryModule
import com.tvdztech.tetris.di.useCaseModule
import com.tvdztech.tetris.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                viewModelModule,
                repositoryModule,
                networkModule,
                useCaseModule,
                dispatcherModule
            )
        }
    }
}