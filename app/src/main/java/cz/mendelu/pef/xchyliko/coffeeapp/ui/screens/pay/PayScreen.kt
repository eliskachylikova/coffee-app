package cz.mendelu.pef.xchyliko.coffeeapp.ui.screens.pay

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CopyAll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import cz.mendelu.pef.xchyliko.coffeeapp.R
import cz.mendelu.pef.xchyliko.coffeeapp.navigation.INavigationRouter
import cz.mendelu.pef.xchyliko.coffeeapp.ui.elements.MenuScreen
import cz.mendelu.pef.xchyliko.coffeeapp.ui.elements.rememberQrBitmapPainter
import org.koin.androidx.compose.getViewModel


@Composable
fun PayScreen(
    navigation: INavigationRouter,
    navController: NavController,
    viewModel: PayViewModel = getViewModel()
) {


    var data: PayData by remember {
        mutableStateOf(viewModel.data)
    }

    viewModel.payUIState.value.let {
        when (it) {
            PayUIState.Default -> {

            }
            PayUIState.Loading -> {
                viewModel.init()
            }
            PayUIState.Paid -> {
                LaunchedEffect(it) {
                    navigation.navigateBack()
                }
            }
            PayUIState.Changed -> {
                data = viewModel.data
                viewModel.payUIState.value = PayUIState.Default
            }
        }
    }

    MenuScreen(
        appBarTitle = stringResource(id = R.string.pay),
        route = navController.currentDestination?.route ?: "",
        navController = navController
    ) {
        PayScreenContent(
            actions = viewModel,
            data = data
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PayScreenContent(
    actions: PayActions,
    data: PayData
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxSize()
    ) {
        if (!data.loading) {
            val bankAccount = "1877520015/3030"

            Text(text = stringResource(R.string.pay_description))

            Image(
                painter = rememberQrBitmapPainter(content = "SPD*1.0*ACC:CZ8930300000001877520015*AM:${data.debt}*CC:CZK*MSG:KAFE"),
                contentDescription = "",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 8.dp)
            )

            Text(text = stringResource(R.string.pay_description_2))

            val clipboardManager: ClipboardManager = LocalClipboardManager.current


            OutlinedTextField(
                value = bankAccount,
                onValueChange = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                trailingIcon = {
                    IconButton(onClick = {
                        clipboardManager.setText(AnnotatedString((bankAccount)))
                    }) {
                        Icon(imageVector = Icons.Filled.CopyAll, contentDescription = "")
                    }
                },
                readOnly = true
            )

            Text(text = stringResource(R.string.current_debt))

            OutlinedTextField(
                value = data.debt.toString() + " " + stringResource(R.string.currency),
                onValueChange = { actions.onDebtChange(data.debt) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                trailingIcon = {
                    IconButton(onClick = {
                        clipboardManager.setText(AnnotatedString((data.debt.toString())))
                    }) {
                        Icon(imageVector = Icons.Filled.CopyAll, contentDescription = "")
                    }
                },
                readOnly = true
            )

            Button(
                onClick = { actions.markAsPaid() },
                modifier = Modifier
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiaryContainer)
            ) {
                Text(
                    text = stringResource(R.string.mark_as_paid),
                    color = MaterialTheme.colorScheme.onTertiaryContainer
                )
            }
        }
    }
}

