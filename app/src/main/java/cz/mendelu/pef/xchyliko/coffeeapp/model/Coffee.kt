package cz.mendelu.pef.xchyliko.coffeeapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coffee")
data class Coffee(
    @ColumnInfo(name = "size")
    var size: Float,

    @ColumnInfo(name = "price")
    var price: Float,

    @ColumnInfo(name = "persons")
    var persons: Int,

    @ColumnInfo(name = "date")
    var date: Long
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null

    @ColumnInfo(name = "paid")
    var paid: Boolean = false
}