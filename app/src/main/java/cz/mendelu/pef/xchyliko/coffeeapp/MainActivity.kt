package cz.mendelu.pef.xchyliko.coffeeapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier

import cz.mendelu.pef.xchyliko.coffeeapp.architecture.BaseActivity
import cz.mendelu.pef.xchyliko.coffeeapp.navigation.Destination
import cz.mendelu.pef.xchyliko.coffeeapp.navigation.NavGraph
import cz.mendelu.pef.xchyliko.coffeeapp.ui.activities.MainActivityViewModel
import cz.mendelu.pef.xchyliko.coffeeapp.ui.theme.CoffeeAppTheme
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : BaseActivity<MainActivityViewModel>(MainActivityViewModel::class.java) {

    override val viewModel: MainActivityViewModel by viewModel()

    companion object {
        fun createIntent(context: AppCompatActivity): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoffeeAppTheme {
                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
                    NavGraph(startDestination = Destination.MainScreen.route)
//                }
            }
        }
    }
}