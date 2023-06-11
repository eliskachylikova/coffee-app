package cz.mendelu.pef.xchyliko.coffeeapp.ui.screens.addEditCoffee

import cz.mendelu.pef.xchyliko.coffeeapp.model.Coffee

class AddEditCoffeeData {
    var coffee: Coffee = Coffee(0f, 0f, 1, 0)
    var loading: Boolean = true
    var coffeeSizeError: Int? = null
}