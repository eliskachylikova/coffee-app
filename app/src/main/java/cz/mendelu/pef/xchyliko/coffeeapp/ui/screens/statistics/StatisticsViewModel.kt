package cz.mendelu.pef.xchyliko.coffeeapp.ui.screens.statistics

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import cz.mendelu.pef.xchyliko.coffeeapp.architecture.BaseViewModel
import cz.mendelu.pef.xchyliko.coffeeapp.database.ICoffeeRepository
import cz.mendelu.pef.xchyliko.coffeeapp.ui.screens.main.MainUIState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class StatisticsViewModel(
    private val repository: ICoffeeRepository
) : BaseViewModel() {

    var statisticsUIState: MutableState<StatisticsUIState> =
        mutableStateOf(StatisticsUIState.Default)

    fun loadCoffee() {
        launch {
            repository.getAll().collect {
                statisticsUIState.value = StatisticsUIState.Success(it)
            }
        }
    }
}