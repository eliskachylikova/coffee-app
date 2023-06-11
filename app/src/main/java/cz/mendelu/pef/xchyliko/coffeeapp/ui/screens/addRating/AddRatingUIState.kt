package cz.mendelu.pef.xchyliko.coffeeapp.ui.screens.addRating

sealed class AddRatingUIState {
    object Loading : AddRatingUIState()
    object Default : AddRatingUIState()
    object RatingSaved : AddRatingUIState()
    object RatingChanged : AddRatingUIState()
}