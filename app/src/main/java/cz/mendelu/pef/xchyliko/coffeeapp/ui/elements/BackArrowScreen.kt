package cz.mendelu.pef.xchyliko.coffeeapp.ui.elements

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BackArrowScreen(
    appBarTitle: String,
    onBackClick: () -> Unit,
    drawFullScreenContent: Boolean = false,
    content: @Composable (paddingValues: PaddingValues) -> Unit // pokud je posledni, obsah tohoto parametru muzeme definovat ve slozenych zavorkach dole
) {

    Scaffold(
        topBar = {
            MediumTopAppBar(title = {
                Text(text = appBarTitle)
            },
                navigationIcon = {
                    IconButton(onClick = {
                        onBackClick()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "")
                    }
                })
        }
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