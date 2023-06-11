package cz.mendelu.pef.xchyliko.coffeeapp.datastore

interface IDataStoreRepository {
    suspend fun setFirstRun()
    suspend fun getFirstRun(): Boolean
}