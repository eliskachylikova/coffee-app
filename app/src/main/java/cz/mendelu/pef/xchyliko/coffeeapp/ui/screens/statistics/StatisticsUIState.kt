package cz.mendelu.pef.xchyliko.coffeeapp.ui.screens.statistics

import cz.mendelu.pef.xchyliko.coffeeapp.model.Coffee

sealed class StatisticsUIState {
    object Default : StatisticsUIState()
    class Success(val coffee: List<Coffee>) : StatisticsUIState()
}
