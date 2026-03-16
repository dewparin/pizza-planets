package com.example.pizzaplanets.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.pizzaplanets.data.entity.Order
import com.example.pizzaplanets.data.entity.OrderPizzaCrossRef
import com.example.pizzaplanets.data.entity.Pizza
import com.example.pizzaplanets.data.entity.Planet

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