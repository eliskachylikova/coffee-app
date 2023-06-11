package cz.mendelu.pef.xchyliko.coffeeapp.ui.screens.rating

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import cz.mendelu.pef.xchyliko.coffeeapp.R
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import cz.mendelu.pef.xchyliko.coffeeapp.model.Rating
import cz.mendelu.pef.xchyliko.coffeeapp.navigation.INavigationRouter
import cz.mendelu.pef.xchyliko.coffeeapp.ui.elements.MenuScreen
import cz.mendelu.pef.xchyliko.coffeeapp.ui.elements.RatingRow
import org.koin.androidx.compose.getViewModel

@Composable
fun RatingScreen(
    navigation: INavigationRouter,
    navController: NavController,
    viewModel: RatingViewModel = getViewModel()
) {
    val ratings = viewModel.ratings

    MenuScreen(
        appBarTitle = stringResource(id = R.string.rating),
        route = navController.currentDestination?.route ?: "",
        navController = navController,
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navigation.navigateToAddRatingScreen()
            },
                containerColor = MaterialTheme.colorScheme.tertiaryContainer) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = null
                )
            }
        }
    ) {
        RatingScreenContent(ratings)
    }

    DisposableEffect(viewModel) {
        viewModel.addListener()
        onDispose { viewModel.removeListener() }
    }
}

@Composable
fun RatingScreenContent(
    ratings: Map<String, Rating>
) {
    val groupedRatings = ratings.values.groupBy { it.coffee }

    val averageRatings = groupedRatings.mapValues { (_, ratings) ->
        ratings.map { it.stars }.average()
    }.toList().sortedByDescending { (_, average) -> average }.toMap()

    averageRatings.forEach {
        if (it == averageRatings.entries.first())
            RatingRow(
                coffeeName = it.key,
                stars = it.value,
                icon = R.drawable.baseline_emoji_events_24,
                iconColor = Color(0xFFDAB060))
        else
            RatingRow(
                coffeeName = it.key,
                stars = it.value,
                icon = R.drawable.baseline_coffee_24)
    }
}