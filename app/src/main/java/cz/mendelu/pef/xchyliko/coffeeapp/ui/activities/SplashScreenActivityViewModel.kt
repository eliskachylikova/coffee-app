package cz.mendelu.pef.xchyliko.coffeeapp.ui.activities

import cz.mendelu.pef.xchyliko.coffeeapp.architecture.BaseViewModel
import cz.mendelu.pef.xchyliko.coffeeapp.datastore.IDataStoreRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SplashScreenActivityViewModel(
    private val dataStoreRepository: IDataStoreRepository
) : BaseViewModel() {
    private val _splashScreenState = MutableStateFlow<SplashScreenUiState>(SplashScreenUiState.Default)
    val splashScreenState: StateFlow<SplashScreenUiState> = _splashScreenState

    fun checkAppState(){
        launch {
            if (dataStoreRepository.getFirstRun()){
                _splashScreenState.value = SplashScreenUiState.RunForAFirstTime
            } else {
                _splashScreenState.value = SplashScreenUiState.ContinueToApp
            }
        }
    }
}