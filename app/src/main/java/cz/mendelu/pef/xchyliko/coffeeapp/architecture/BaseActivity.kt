package cz.mendelu.pef.xchyliko.coffeeapp.architecture

import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModel

abstract class BaseActivity<out VM : ViewModel>(private val viewModelClass: Class<VM>) : ComponentActivity() {
    protected abstract val viewModel: VM
}