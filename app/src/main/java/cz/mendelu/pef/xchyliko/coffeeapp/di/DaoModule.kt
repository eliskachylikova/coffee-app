package cz.mendelu.pef.xchyliko.coffeeapp.di

import cz.mendelu.pef.xchyliko.coffeeapp.database.CoffeeDao
import cz.mendelu.pef.xchyliko.coffeeapp.database.CoffeeDatabase
import org.koin.dsl.module

val daoModule = module {

    fun provideDaoModule(database: CoffeeDatabase): CoffeeDao = database.coffeeDao()

    single { provideDaoModule(get()) }

}