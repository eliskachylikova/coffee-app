package cz.mendelu.pef.xchyliko.coffeeapp.di

import androidx.lifecycle.SavedStateHandle
import cz.mendelu.pef.xchyliko.coffeeapp.ui.activities.AppIntroViewModel
import cz.mendelu.pef.xchyliko.coffeeapp.ui.activities.MainActivityViewModel
import cz.mendelu.pef.xchyliko.coffeeapp.ui.activities.SplashScreenActivityViewModel
import cz.mendelu.pef.xchyliko.coffeeapp.ui.screens.addEditCoffee.AddEditCoffeeViewModel
import cz.mendelu.pef.xchyliko.coffeeapp.ui.screens.addRating.AddRatingViewModel
import cz.mendelu.pef.xchyliko.coffeeapp.ui.screens.main.MainViewModel
import cz.mendelu.pef.xchyliko.coffeeapp.ui.screens.pay.PayViewModel
import cz.mendelu.pef.xchyliko.coffeeapp.ui.screens.rating.RatingViewModel
import cz.mendelu.pef.xchyliko.coffeeapp.ui.screens.statistics.StatisticsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    // screens
    viewModel { MainViewModel(get()) }
    viewModel { AddEditCoffeeViewModel(get()) }
    viewModel { PayViewModel(get()) }
    viewModel { RatingViewModel(get()) }
    viewModel { AddRatingViewModel(get()) }
    viewModel { StatisticsViewModel(get()) }

    // activities
    viewModel { SplashScreenActivityViewModel(get()) }
    viewModel { MainActivityViewModel() }
    viewModel { AppIntroViewModel(get()) }

    // For the saved state handle
    fun provideSavedStateHandle(): SavedStateHandle {
        return SavedStateHandle()
    }

    factory { provideSavedStateHandle() }
}