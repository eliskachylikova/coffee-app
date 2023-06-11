package cz.mendelu.pef.xchyliko.coffeeapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cz.mendelu.pef.xchyliko.coffeeapp.BuildConfig
import cz.mendelu.pef.xchyliko.coffeeapp.R
import cz.mendelu.pef.xchyliko.coffeeapp.navigation.INavigationRouter
import cz.mendelu.pef.xchyliko.coffeeapp.ui.elements.BackArrowScreen

@Composable
fun InfoScreen(
    navigation: INavigationRouter
) {
    BackArrowScreen(
        appBarTitle = stringResource(R.string.app_info),
        onBackClick = { navigation.navigateBack() },
    ) {
        InfoScreenContent()
    }
}

@Composable
fun InfoScreenContent() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
    ) {
        Text(text = stringResource(R.string.app_version) + " " + BuildConfig.VERSION_NAME)
    }
}
