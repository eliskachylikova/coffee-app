package cz.mendelu.pef.xchyliko.coffeeapp.ui.activities

sealed class SplashScreenUiState {
    object Default : SplashScreenUiState()
    object RunForAFirstTime : SplashScreenUiState()
    object ContinueToApp : SplashScreenUiState()
}