package cz.mendelu.pef.xchyliko.coffeeapp.model

import com.google.firebase.firestore.Exclude

data class Rating(

    @Exclude var id: String = "",
    var coffee: String = "",
    var stars: Float = 0f

)
