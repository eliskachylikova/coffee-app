package cz.mendelu.pef.xchyliko.coffeeapp.ui.screens.addRating


interface AddRatingActions {
    fun saveRating()
    fun onCoffeeChange(coffee: String)
    fun onStarsChange(stars: Float)
}