package cz.mendelu.pef.xchyliko.coffeeapp.di

import cz.mendelu.pef.xchyliko.coffeeapp.firestore.IStorageService
import cz.mendelu.pef.xchyliko.coffeeapp.firestore.StorageServiceImpl
import org.koin.dsl.module


val storageModule = module {

    fun provideStorageModule(): IStorageService {
        return StorageServiceImpl() // get() se pouziva jen na veci s DI, jinak ne, takze tu neni
    }

    single { provideStorageModule() }

}