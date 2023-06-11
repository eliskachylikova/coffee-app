package cz.mendelu.pef.xchyliko.coffeeapp.navigation

import androidx.navigation.NavController

interface INavigationRouter {

    fun navigateBack()
    fun navigateToAddEditCoffeeScreen(id: Long?)
    fun navigateToAddRatingScreen()
    fun navigateToInfoScreen()
    fun getNavController(): NavController

}