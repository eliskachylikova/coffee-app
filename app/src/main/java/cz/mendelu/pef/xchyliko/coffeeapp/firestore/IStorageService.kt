package cz.mendelu.pef.xchyliko.coffeeapp.firestore

import cz.mendelu.pef.xchyliko.coffeeapp.model.Rating

interface IStorageService {
    fun addListener(
        onDocumentEvent: (Boolean, Rating) -> Unit,
        onError: (Throwable) -> Unit
    )
    fun removeListener()
    fun saveRating(rating: Rating)
}