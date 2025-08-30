package com.github.abhiram0106.lobotus_assignment.core

import android.app.Application
import com.github.abhiram0106.lobotus_assignment.core.di.httpClientModule
import com.github.abhiram0106.lobotus_assignment.feature_companies.di.companyModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApp)
            modules(
                httpClientModule,
                companyModule,
            )
        }
    }
}