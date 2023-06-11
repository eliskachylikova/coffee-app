package cz.mendelu.pef.xchyliko.coffeeapp.database

import androidx.room.*
import cz.mendelu.pef.xchyliko.coffeeapp.model.Coffee
import kotlinx.coroutines.flow.Flow

@Dao
interface CoffeeDao {

    @Query("SELECT * FROM coffee ORDER BY date DESC")
    fun getAll(): Flow<List<Coffee>>

    @Query("SELECT * FROM coffee WHERE id = :id")
    suspend fun getCoffeeById(id: Long) : Coffee

    @Insert
    suspend fun insert(coffee: Coffee): Long

    @Update
    suspend fun update(coffee: Coffee)

    @Delete
    suspend fun delete(coffee: Coffee)

    @Query("UPDATE coffee SET paid = true")
    suspend fun markCoffeeAsPaid()

    @Query("SELECT ROUND(SUM(price/persons), 2) FROM coffee WHERE paid = false")
    suspend fun countDebt(): Float?
}