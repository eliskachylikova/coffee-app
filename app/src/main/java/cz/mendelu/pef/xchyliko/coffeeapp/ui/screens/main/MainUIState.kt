package cz.mendelu.pef.xchyliko.coffeeapp.ui.screens.main

import cz.mendelu.pef.xchyliko.coffeeapp.model.Coffee

sealed class MainUIState {
    object Default : MainUIState()
    class Success(val coffee: List<Coffee>) : MainUIState()
}