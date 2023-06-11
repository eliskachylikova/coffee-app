package cz.mendelu.pef.xchyliko.coffeeapp.ui.screens.pay

sealed class PayUIState {

    object Loading : PayUIState()
    object Default : PayUIState()
    object Changed : PayUIState()
    object Paid : PayUIState()

}