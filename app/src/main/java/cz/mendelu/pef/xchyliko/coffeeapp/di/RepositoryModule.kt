package cz.mendelu.pef.xchyliko.coffeeapp.di

import cz.mendelu.pef.xchyliko.coffeeapp.database.CoffeeDao
import cz.mendelu.pef.xchyliko.coffeeapp.database.CoffeeRepositoryImpl
import cz.mendelu.pef.xchyliko.coffeeapp.database.ICoffeeRepository
import org.koin.dsl.module

val repositoryModule = module {

    fun provideTasksRepository(dao: CoffeeDao): ICoffeeRepository {
        return CoffeeRepositoryImpl(dao) // get() se pouziva jen na veci s DI, jinak ne, takze tu neni
    }

    single { provideTasksRepository(get()) }

}