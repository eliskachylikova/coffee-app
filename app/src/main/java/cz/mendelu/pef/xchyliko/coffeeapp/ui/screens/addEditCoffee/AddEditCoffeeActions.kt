package cz.mendelu.pef.xchyliko.coffeeapp.ui.screens.addEditCoffee

import cz.mendelu.pef.xchyliko.coffeeapp.model.Coffee

interface AddEditCoffeeActions {
    fun saveCoffee()
    fun onSizeChange(coffee: Coffee)
    fun onCountChangeMinus()
    fun onCountChangePlus() }