package cz.mendelu.pef.xchyliko.coffeeapp.di

import android.content.Context
import cz.mendelu.pef.xchyliko.coffeeapp.datastore.DataStoreRepositoryImpl
import cz.mendelu.pef.xchyliko.coffeeapp.datastore.IDataStoreRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataStoreModule = module {
    single { provideDataStoreRepository(androidContext()) }
}

fun provideDataStoreRepository(context: Context): IDataStoreRepository
        = DataStoreRepositoryImpl(context)