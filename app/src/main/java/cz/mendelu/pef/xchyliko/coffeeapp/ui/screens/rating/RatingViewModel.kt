package cz.mendelu.pef.xchyliko.coffeeapp.ui.screens.rating

import androidx.compose.runtime.mutableStateMapOf
import androidx.lifecycle.viewModelScope
import cz.mendelu.pef.xchyliko.coffeeapp.architecture.BaseViewModel
import cz.mendelu.pef.xchyliko.coffeeapp.firestore.IStorageService
import cz.mendelu.pef.xchyliko.coffeeapp.model.Rating
import kotlinx.coroutines.launch

class RatingViewModel (
    private val storageService: IStorageService
): BaseViewModel() {

    var ratings = mutableStateMapOf<String, Rating>()
        private set

    fun addListener() {
        viewModelScope.launch() {
            storageService.addListener(::onDocumentEvent) {}
        }
    }

    fun removeListener() {
        viewModelScope.launch() { storageService.removeListener() }
    }

    private fun onDocumentEvent(wasDocumentDeleted: Boolean, rating: Rating) {
        if (wasDocumentDeleted) ratings.remove(rating.id) else ratings[rating.id] = rating
    }
}