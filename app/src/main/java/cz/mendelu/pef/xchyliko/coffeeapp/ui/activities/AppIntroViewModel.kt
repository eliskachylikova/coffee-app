package cz.mendelu.pef.xchyliko.coffeeapp.ui.activities

import cz.mendelu.pef.xchyliko.coffeeapp.architecture.BaseViewModel
import cz.mendelu.pef.xchyliko.coffeeapp.datastore.IDataStoreRepository

class AppIntroViewModel(private val dataStoreRepository: IDataStoreRepository) : BaseViewModel() {
    suspend fun setFirstRun(){
        dataStoreRepository.setFirstRun()
    }
}