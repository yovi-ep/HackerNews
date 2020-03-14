package yovi.putra.hackernews.core

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import yovi.putra.hackernews.BuildConfig
import yovi.putra.hackernews.di.appModules

class AppContext : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(
                if (BuildConfig.DEBUG) Level.DEBUG else Level.INFO
            )
            androidContext(this@AppContext)
            modules(appModules)
        }
    }
}