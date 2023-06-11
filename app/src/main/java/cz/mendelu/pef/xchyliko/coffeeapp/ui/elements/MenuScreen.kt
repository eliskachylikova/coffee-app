package cz.mendelu.pef.xchyliko.coffeeapp.ui.elements

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import cz.mendelu.pef.xchyliko.coffeeapp.ui.screens.NavigationStuff
import cz.mendelu.pef.xchyliko.coffeeapp.ui.screens.getNavigationStuffByRoute
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(
    appBarTitle: String,
    route: String,
    navController: NavController,
    floatingActionButton: @Composable () -> Unit = {},
    drawFullScreenContent: Boolean = false,
    content: @Composable (paddingValues: PaddingValues) -> Unit
) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val items = NavigationStuff.values()
//    val selectedItem = remember { mutableStateOf(getNavigationStuffByTitle(appBarTitle)) }
    val selectedItem = remember {
        mutableStateOf(getNavigationStuffByRoute(route))
    }

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
                    MediumTopAppBar(title = {
                        Text(text = appBarTitle)
                    },
                        navigationIcon = {
                            IconButton(onClick = {
                                scope.launch {
                                    // opens drawer
                                    drawerState.open()
                                }
                            }) {
                                Icon(
                                    imageVector = Icons.Filled.Menu,
                                    contentDescription = "")
                            }
                        })
                },
                floatingActionButton = floatingActionButton
            ) {
                if (!drawFullScreenContent) {
                    LazyColumn(modifier = Modifier.padding(it)){
                        item {
                            content(it)
                        }
                    }
                } else {
                    content(it)
                }
            }
        }
    )
}