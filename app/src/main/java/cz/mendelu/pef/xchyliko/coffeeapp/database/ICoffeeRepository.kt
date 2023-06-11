package cz.mendelu.pef.xchyliko.coffeeapp.database

import cz.mendelu.pef.xchyliko.coffeeapp.model.Coffee
import kotlinx.coroutines.flow.Flow


interface ICoffeeRepository {

    fun getAll(): Flow<List<Coffee>>
    suspend fun insert(coffee: Coffee): Long
    suspend fun update(coffee: Coffee)
    suspend fun getCoffeeById(id: Long) : Coffee
    suspend fun delete(coffee: Coffee)
    suspend fun markCoffeeAsPaid()
    suspend fun countDebt(): Float?
}