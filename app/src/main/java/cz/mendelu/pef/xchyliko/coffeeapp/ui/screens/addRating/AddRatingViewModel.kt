package cz.mendelu.pef.xchyliko.coffeeapp.ui.screens.addRating

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import cz.mendelu.pef.xchyliko.coffeeapp.R
import cz.mendelu.pef.xchyliko.coffeeapp.architecture.BaseViewModel
import cz.mendelu.pef.xchyliko.coffeeapp.firestore.IStorageService

class AddRatingViewModel(
    private val storageService: IStorageService
) : BaseViewModel(), AddRatingActions {

    var data: AddRatingData = AddRatingData()

    var addRatingUIState: MutableState<AddRatingUIState> =
        mutableStateOf(AddRatingUIState.Loading)

    override fun saveRating() {
        if (!data.rating.coffee.isEmpty() && data.rating.stars > 0) {
            storageService.saveRating(data.rating)
            addRatingUIState.value = AddRatingUIState.RatingSaved
            return
        }
        
        if (data.rating.coffee.isEmpty()) {
            data.coffeeError = R.string.coffeeError
            addRatingUIState.value = AddRatingUIState.RatingChanged
        }

        if (data.rating.stars <= 0) {
            data.starsError = R.string.starsError
            addRatingUIState.value = AddRatingUIState.RatingChanged
        }
    }

    override fun onCoffeeChange(coffee: String) {
        data.rating.coffee = coffee
        addRatingUIState.value = AddRatingUIState.RatingChanged
    }

    override fun onStarsChange(stars: Float) {
        data.rating.stars = stars
        addRatingUIState.value = AddRatingUIState.RatingChanged
    }

}