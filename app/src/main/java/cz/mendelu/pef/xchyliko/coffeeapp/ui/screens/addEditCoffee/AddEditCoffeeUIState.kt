package cz.mendelu.pef.xchyliko.coffeeapp.ui.screens.addEditCoffee

sealed class AddEditCoffeeUIState {

    object Loading : AddEditCoffeeUIState()
    object Default : AddEditCoffeeUIState()
    object CoffeeSaved : AddEditCoffeeUIState()
    object CoffeeChanged : AddEditCoffeeUIState()

}