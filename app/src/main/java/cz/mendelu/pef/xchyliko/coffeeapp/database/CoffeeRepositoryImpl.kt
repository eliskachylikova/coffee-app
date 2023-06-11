package cz.mendelu.pef.xchyliko.coffeeapp.database

import cz.mendelu.pef.xchyliko.coffeeapp.model.Coffee
import kotlinx.coroutines.flow.Flow

class CoffeeRepositoryImpl(private val coffeeDao: CoffeeDao) : ICoffeeRepository {

    override fun getAll(): Flow<List<Coffee>> {
        return coffeeDao.getAll()
    }

    override suspend fun insert(coffee: Coffee): Long {
        return coffeeDao.insert(coffee)
    }

    override suspend fun update(coffee: Coffee) {
        coffeeDao.update(coffee)
    }

    override suspend fun getCoffeeById(id: Long): Coffee = coffeeDao.getCoffeeById(id)

    override suspend fun delete(coffee: Coffee) {
        coffeeDao.delete(coffee)
    }

    override suspend fun markCoffeeAsPaid() {
        coffeeDao.markCoffeeAsPaid()
    }

    override suspend fun countDebt(): Float? {
        return coffeeDao.countDebt()
    }
}