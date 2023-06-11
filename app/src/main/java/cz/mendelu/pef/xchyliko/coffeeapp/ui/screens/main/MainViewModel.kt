package cz.mendelu.pef.xchyliko.coffeeapp.ui.screens.main

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import cz.mendelu.pef.xchyliko.coffeeapp.architecture.BaseViewModel
import cz.mendelu.pef.xchyliko.coffeeapp.database.ICoffeeRepository
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: ICoffeeRepository
) : BaseViewModel(), MainActions {

    val mainUIState: MutableState<MainUIState> =
        mutableStateOf(MainUIState.Default)

    fun loadCoffee() {
        launch {
            repository.getAll().collect {
                mainUIState.value = MainUIState.Success(it)
            }
        }
    }

    override fun deleteCoffee(id: Long) {
        launch {
            repository.delete(repository.getCoffeeById(id))
        }
        loadCoffee()
    }

}