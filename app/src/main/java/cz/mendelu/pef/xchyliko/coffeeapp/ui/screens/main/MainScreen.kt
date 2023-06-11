package cz.mendelu.pef.xchyliko.coffeeapp.ui.screens.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import cz.mendelu.pef.xchyliko.coffeeapp.model.Coffee
import cz.mendelu.pef.xchyliko.coffeeapp.navigation.INavigationRouter
import org.koin.androidx.compose.getViewModel
import cz.mendelu.pef.xchyliko.coffeeapp.R
import cz.mendelu.pef.xchyliko.coffeeapp.ui.screens.NavigationStuff
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navigation: INavigationRouter,
    navController: NavController,
    viewModel: MainViewModel = getViewModel(),
) {

    var coffee = remember {
        mutableStateListOf<Coffee>()
    }

    viewModel.mainUIState.value.let {
        when (it) {
            MainUIState.Default -> {
                viewModel.loadCoffee()
            }
            is MainUIState.Success -> {
                coffee.clear()
                coffee.addAll(it.coffee)
            }
        }
    }

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val items = NavigationStuff.values()
    val selectedItem = remember { mutableStateOf(items[0]) }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Spacer(Modifier.height(12.dp))
                items.forEach { item ->
                    NavigationDrawerItem(
                        icon = { Icon(item.imageVector, contentDescription = null) },
                        label = { Text(stringResource(id = item.titleId)) },
                        selected = item == selectedItem.value,
                        onClick = {
                            scope.launch { drawerState.close() }
                            navController.navigate(item.route)
                            selectedItem.value = item
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                }
            }
        },
        content = {
            Scaffold(
                topBar = {
                    CenterAlignedTopAppBar(
                        title = { Text(stringResource(id = R.string.app_title)) },
                        navigationIcon = {
                            IconButton(onClick = {
                                scope.launch {
                                    drawerState.open()
                                }
                            }) {
                                Icon(
                                    imageVector = Icons.Filled.Menu,
                                    contentDescription = ""
                                )
                            }
                        },
                        actions = {
                            IconButton(onClick = { navigation.navigateToInfoScreen() }) {
                                Icon(
                                    imageVector = Icons.Outlined.Info,
                                    contentDescription = ""
                                )
                            }
                        }
                    )
                },
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = {
                            navigation.navigateToAddEditCoffeeScreen(-1L)
                        },
                        containerColor = MaterialTheme.colorScheme.tertiaryContainer
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_coffee_24),
                            contentDescription = null
                        )
                    }
                }
            ) {
                MainScreenContent(
                    paddingValues = it,
                    coffee = coffee,
                    navigation = navigation,
                    actions = viewModel
                )
            }
        }
    )
}

@Composable
fun MainScreenContent(
    paddingValues: PaddingValues,
    coffee: MutableList<Coffee>,
    navigation: INavigationRouter,
    actions: MainActions
) {
    LazyColumn(modifier = Modifier.padding(paddingValues)) {
        coffee.forEach {
            item(key = it.id) {
                CoffeeRow(
                    coffee = it,
                    onRowClick = { navigation.navigateToAddEditCoffeeScreen(it.id) },
                    actions = actions
                )
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoffeeRow(
    coffee: Coffee,
    onRowClick: () -> Unit,
    actions: MainActions
) {
    ListItem(
        headlineText = { Text(text = coffee.size.roundToInt().toString() + " " + stringResource(id = R.string.milliliter)) },
        modifier = Modifier.clickable(onClick = onRowClick),
        leadingContent = {
            Icon(
                painter = painterResource(id = R.drawable.baseline_coffee_24),
                contentDescription = null
            )
        },
        trailingContent = {
            TextButton(
                onClick = { coffee.id?.let { actions.deleteCoffee(it) } }
            ) {
                Icon(
                    Icons.Default.Delete,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    )
}