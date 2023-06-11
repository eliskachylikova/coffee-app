package cz.mendelu.pef.xchyliko.coffeeapp.navigation

sealed class Destination(val route: String){

    object MainScreen : Destination(route = "main")
    object AddEditCoffeeScreen : Destination(route = "add_edit_coffee")
    object PayScreen : Destination(route = "pay")
    object RatingScreen : Destination(route = "rating")
    object StatisticsScreen : Destination(route = "statistics")
    object AddRatingScreen : Destination(route = "add_rating")
    object InfoScreen : Destination(route = "info")
}