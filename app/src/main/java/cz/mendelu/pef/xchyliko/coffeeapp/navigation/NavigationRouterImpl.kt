package cz.mendelu.pef.xchyliko.coffeeapp.navigation

import androidx.navigation.NavController

class NavigationRouterImpl(
    private val navController: NavController
) : INavigationRouter {

    override fun navigateBack() {
        navController.popBackStack()
    }

    override fun navigateToAddEditCoffeeScreen(id: Long?) {
        navController
            .navigate(
                Destination.AddEditCoffeeScreen.route + "/" + id
            )
    }

    override fun navigateToAddRatingScreen() {
        navController.navigate(
            Destination.AddRatingScreen.route
        )
    }

    override fun navigateToInfoScreen() {
        navController.navigate(
            Destination.InfoScreen.route
        )
    }

    override fun getNavController(): NavController = navController

}