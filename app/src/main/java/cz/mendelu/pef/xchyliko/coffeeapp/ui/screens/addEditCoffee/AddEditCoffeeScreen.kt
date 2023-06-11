package cz.mendelu.pef.xchyliko.coffeeapp.ui.screens.addEditCoffee

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cz.mendelu.pef.xchyliko.coffeeapp.R
import cz.mendelu.pef.xchyliko.coffeeapp.model.Coffee
import cz.mendelu.pef.xchyliko.coffeeapp.navigation.INavigationRouter
import cz.mendelu.pef.xchyliko.coffeeapp.ui.elements.BackArrowScreen
import cz.mendelu.pef.xchyliko.coffeeapp.ui.elements.Counter
import org.koin.androidx.compose.getViewModel
import kotlin.math.roundToInt

@Composable
fun AddEditCoffeeScreen(
    navigation: INavigationRouter,
    viewModel: AddEditCoffeeViewModel = getViewModel(),
    id: Long?
) {

    viewModel.coffeeId = id

    var data: AddEditCoffeeData by remember {
        mutableStateOf(viewModel.data)
    }

    viewModel.addEditCoffeeUIState.value.let {
        when (it) {
            AddEditCoffeeUIState.Default -> {

            }
            AddEditCoffeeUIState.CoffeeChanged -> {
                data = viewModel.data // rekompozice
                viewModel.addEditCoffeeUIState.value = AddEditCoffeeUIState.Default
            }
            AddEditCoffeeUIState.CoffeeSaved -> {
                LaunchedEffect(it) {
                    navigation.navigateBack()
                }
            }
            AddEditCoffeeUIState.Loading -> {
                viewModel.initCoffee()
            }
        }
    }

    BackArrowScreen(
        appBarTitle = stringResource(R.string.have_coffee),
        onBackClick = { navigation.navigateBack() },
    ) {
        AddEditCoffeeScreenContent(
            actions = viewModel,
            data = data
        )
    }
}

@Composable
fun AddEditCoffeeScreenContent(
    actions: AddEditCoffeeActions,
    data: AddEditCoffeeData
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        CoffeeSizeRadio(
            coffee = data.coffee,
            errorMessage = if (data.coffeeSizeError != null) stringResource(data.coffeeSizeError!!) else ""
        ) { actions.onSizeChange(data.coffee) }

        Counter(
            data.coffee.persons,
            { actions.onCountChangeMinus() },
            { actions.onCountChangePlus() }
        )

        Button(
            onClick = { actions.saveCoffee() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
        ) {
            Text(
                text = stringResource(R.string.save),
                color = MaterialTheme.colorScheme.onTertiaryContainer
            )
        }
    }
}


@Composable
fun CoffeeSizeRadio(
    coffee: Coffee,
    errorMessage: String,
    onClick: () -> Unit
) {
    val coffeeOptions = CoffeeSize.values()

    Column {
        Text(text = stringResource(R.string.select_size), modifier = Modifier.padding(horizontal = 16.dp))
        coffeeOptions.forEach { coffeeOption ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = (coffeeOption == getCoffeeSizeBySize(coffee.size)),
                        onClick = {
                            coffee.size = coffeeOption.size
                            coffee.price = coffeeOption.price
                            onClick()
                        }
                    )
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (coffeeOption == getCoffeeSizeBySize(coffee.size)),
                    onClick = {
                        coffee.size = coffeeOption.size
                        coffee.price = coffeeOption.price
                        onClick()
                    }
                )
                Text(
                    text = coffeeOption.size.roundToInt().toString() + " " + stringResource(R.string.milliliter),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
        Text(
            text = errorMessage,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .alpha(if (errorMessage.isNotEmpty()) 100f else 0f),
            color = MaterialTheme.colorScheme.error
        )
    }
}