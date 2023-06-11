package cz.mendelu.pef.xchyliko.coffeeapp.di

import cz.mendelu.pef.xchyliko.coffeeapp.CoffeeApplication
import cz.mendelu.pef.xchyliko.coffeeapp.database.CoffeeDatabase
import org.koin.dsl.module

val databaseModule = module {

    fun provideDatabaseModule(): CoffeeDatabase = CoffeeDatabase.getDatabase(CoffeeApplication.appContext)
    // jedine misto kde muzeme zavolat ToDoApplication

    single { provideDatabaseModule() }

}