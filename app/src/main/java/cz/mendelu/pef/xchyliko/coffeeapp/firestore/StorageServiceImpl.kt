package cz.mendelu.pef.xchyliko.coffeeapp.firestore

import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import cz.mendelu.pef.xchyliko.coffeeapp.model.Rating

class StorageServiceImpl : IStorageService {

    companion object {
        private const val RATING_COLLECTION = "ratings"
    }

    private var listenerRegistration: ListenerRegistration? = null

    override fun addListener(
        onDocumentEvent: (Boolean, Rating) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        val query = Firebase.firestore.collection(RATING_COLLECTION)

        listenerRegistration = query.addSnapshotListener { value, error ->
            if (error != null) {
                onError(error)
                return@addSnapshotListener
            }

            value?.documentChanges?.forEach {
                val wasDocumentDeleted = it.type == DocumentChange.Type.REMOVED
                val rating = it.document.toObject<Rating>().copy(id = it.document.id)
                onDocumentEvent(wasDocumentDeleted, rating)
            }
        }
    }

    override fun removeListener() {
        listenerRegistration?.remove()
    }

    override fun saveRating(rating: Rating) {
        Firebase.firestore
            .collection(RATING_COLLECTION)
            .add(rating)
    }

}