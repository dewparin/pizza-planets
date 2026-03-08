package com.example.pizzaplanets.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pizzaplanets.entity.Order
import com.example.pizzaplanets.entity.OrderPizzaCrossRef
import com.example.pizzaplanets.entity.Pizza
import com.example.pizzaplanets.entity.Planet

@Database(
    version = 3,
    exportSchema = false,
    entities = [
        Planet::class,
        Pizza::class,
        Order::class,
        OrderPizzaCrossRef::class,
    ],
)
abstract class PizzaPlanetsDatabase : RoomDatabase() {
    abstract fun planetDao(): PlanetDao
    abstract fun orderDao(): OrderDao
}