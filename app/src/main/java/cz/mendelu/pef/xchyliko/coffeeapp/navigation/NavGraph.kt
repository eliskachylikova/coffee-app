package cz.mendelu.pef.xchyliko.coffeeapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import cz.mendelu.pef.xchyliko.coffeeapp.ui.screens.InfoScreen
import cz.mendelu.pef.xchyliko.coffeeapp.ui.screens.addEditCoffee.AddEditCoffeeScreen
import cz.mendelu.pef.xchyliko.coffeeapp.ui.screens.addRating.AddRatingScreen
import cz.mendelu.pef.xchyliko.coffeeapp.ui.screens.main.MainScreen
import cz.mendelu.pef.xchyliko.coffeeapp.ui.screens.pay.PayScreen
import cz.mendelu.pef.xchyliko.coffeeapp.ui.screens.rating.RatingScreen
import cz.mendelu.pef.xchyliko.coffeeapp.ui.screens.statistics.StatisticsScreen

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    navigation: INavigationRouter = remember {
        NavigationRouterImpl(navController)
    },
    startDestination: String
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

        composable(Destination.MainScreen.route) {
            MainScreen(navigation, navController)
        }

        composable(Destination.AddEditCoffeeScreen.route + "/{id}",
            arguments = listOf(
                navArgument(name = "id") {
                    type = NavType.LongType
                    defaultValue = -1L
                }
            )
        ) {
            val id = it.arguments?.getLong("id")
            AddEditCoffeeScreen(
                navigation = navigation,
                id = if (id != -1L) id else null
            )
        }

        composable(Destination.PayScreen.route) {
            PayScreen(navigation, navController)
        }

        composable(Destination.RatingScreen.route) {
            RatingScreen(navigation, navController)
        }

        composable(Destination.StatisticsScreen.route) {
            StatisticsScreen(navigation, navController)
        }

        composable(Destination.AddRatingScreen.route) {
            AddRatingScreen(navigation)
        }

        composable(Destination.InfoScreen.route) {
            InfoScreen(navigation)
        }
    }
}