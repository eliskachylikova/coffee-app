package cz.mendelu.pef.xchyliko.coffeeapp

import android.app.Application
import android.content.Context
import cz.mendelu.pef.xchyliko.coffeeapp.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CoffeeApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
        startKoin {
            androidContext(this@CoffeeApplication)
            modules(listOf(
                daoModule,
                databaseModule,
                repositoryModule,
                viewModelModule,
                storageModule,
                dataStoreModule
            ))
        }
    }

    companion object {
        lateinit var appContext: Context
            private set
    }
}