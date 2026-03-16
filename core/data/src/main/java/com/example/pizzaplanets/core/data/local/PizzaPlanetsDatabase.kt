package com.example.pizzaplanets.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.pizzaplanets.core.data.entity.Order
import com.example.pizzaplanets.core.data.entity.OrderPizzaCrossRef
import com.example.pizzaplanets.core.data.entity.Pizza
import com.example.pizzaplanets.core.data.entity.Planet

@TypeConverters(DatabaseTypeConverters::class)
@Database(
    version = 5,
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