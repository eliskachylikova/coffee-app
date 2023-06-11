package cz.mendelu.pef.xchyliko.coffeeapp.ui.screens.addRating

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarStyle
import cz.mendelu.pef.xchyliko.coffeeapp.R
import cz.mendelu.pef.xchyliko.coffeeapp.navigation.INavigationRouter
import cz.mendelu.pef.xchyliko.coffeeapp.ui.elements.BackArrowScreen
import cz.mendelu.pef.xchyliko.coffeeapp.ui.elements.MyTextField
import org.koin.androidx.compose.getViewModel

@Composable
fun AddRatingScreen(
    navigation: INavigationRouter,
    viewModel: AddRatingViewModel = getViewModel()
) {
    var data: AddRatingData by remember {
        mutableStateOf(viewModel.data)
    }

    viewModel.addRatingUIState.value.let {
        when (it) {
            AddRatingUIState.Default -> {

            }
            AddRatingUIState.Loading -> {
            }
            AddRatingUIState.RatingChanged -> {
                data = viewModel.data
                viewModel.addRatingUIState.value = AddRatingUIState.Default
            }
            AddRatingUIState.RatingSaved -> {
                LaunchedEffect(it) {
                    navigation.navigateBack()
                }
            }
        }
    }

    BackArrowScreen(
        appBarTitle = stringResource(R.string.add_rating),
        onBackClick = { navigation.navigateBack() }
    ) {
        AddRatingScreenContent(actions = viewModel, data = data)
    }
}

@Composable
fun AddRatingScreenContent(
    actions: AddRatingActions,
    data: AddRatingData
) {

    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        MyTextField(
            value = data.rating.coffee,
            hint = stringResource(R.string.name_of_coffee),
            onValueChange = { actions.onCoffeeChange(it) },
            error = if (data.coffeeError != null) stringResource(data.coffeeError!!) else ""
        )

        Text(text = stringResource(R.string.rating), modifier = Modifier.padding(vertical = 8.dp))
        RatingBar(
            value = data.rating.stars,
            style = RatingBarStyle.Stroke(
            ),
            onValueChange = {
                data.rating.stars = it
            },
            onRatingChanged = { actions.onStarsChange(it) }
        )
        Text(
            text = if (data.starsError != null) stringResource(data.starsError!!) else "",
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .alpha(if (data.starsError != null) 100f else 0f),
            color = MaterialTheme.colorScheme.error
        )

        Button(
            onClick = { actions.saveRating() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
        ) {
            Text(
                text = stringResource(id = R.string.add),
                color = MaterialTheme.colorScheme.onTertiaryContainer
            )

        }
    }

}