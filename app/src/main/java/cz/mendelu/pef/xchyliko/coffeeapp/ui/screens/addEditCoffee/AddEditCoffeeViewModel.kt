package cz.mendelu.pef.xchyliko.coffeeapp.ui.screens.addEditCoffee

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import cz.mendelu.pef.xchyliko.coffeeapp.R
import cz.mendelu.pef.xchyliko.coffeeapp.architecture.BaseViewModel
import cz.mendelu.pef.xchyliko.coffeeapp.database.ICoffeeRepository
import cz.mendelu.pef.xchyliko.coffeeapp.model.Coffee
import kotlinx.coroutines.launch

class AddEditCoffeeViewModel(
    private val repository: ICoffeeRepository
): BaseViewModel(), AddEditCoffeeActions {

    var data: AddEditCoffeeData = AddEditCoffeeData()
    var coffeeId: Long? = null

    var addEditCoffeeUIState: MutableState<AddEditCoffeeUIState> =
        mutableStateOf(AddEditCoffeeUIState.Loading)

    override fun saveCoffee() {
        if (data.coffee.size == 0f) {
            data.coffeeSizeError = R.string.coffeeSizeError
            addEditCoffeeUIState.value = AddEditCoffeeUIState.CoffeeChanged
        } else {
            launch {
                if (coffeeId == null) {
                    // insert
                    data.coffee.date = System.currentTimeMillis()
                    val id = repository.insert(data.coffee)
                    if (id > 0) {
                        addEditCoffeeUIState.value = AddEditCoffeeUIState.CoffeeSaved
                    } else {
                        // error
                    }
                } else {
                    // update
                    repository.update(data.coffee)
                    addEditCoffeeUIState.value = AddEditCoffeeUIState.CoffeeSaved
                }
            }
        }
    }

    override fun onSizeChange(coffee: Coffee) {
        data.coffee.size = coffee.size
        data.coffee.price = coffee.price
        addEditCoffeeUIState.value = AddEditCoffeeUIState.CoffeeChanged
    }

    override fun onCountChangeMinus() {
        data.coffee.persons--
        addEditCoffeeUIState.value = AddEditCoffeeUIState.CoffeeChanged
    }

    override fun onCountChangePlus() {
        data.coffee.persons++
        addEditCoffeeUIState.value = AddEditCoffeeUIState.CoffeeChanged
    }

    fun initCoffee() {
        if (coffeeId != null) {
            launch {
                data.coffee = repository.getCoffeeById(coffeeId!!)
                data.loading = false
                addEditCoffeeUIState.value = AddEditCoffeeUIState.CoffeeChanged
            }
        } else {
            data.loading = false
            addEditCoffeeUIState.value = AddEditCoffeeUIState.CoffeeChanged
        }
    }
}