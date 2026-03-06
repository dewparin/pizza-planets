package com.example.pizzaplanets.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pizzaplanets.entity.Pizza
import com.example.pizzaplanets.entity.Planet

@Database(
    version = 2,
    exportSchema = false,
    entities = [Planet::class, Pizza::class],
)
abstract class PizzaPlanetsDatabase : RoomDatabase() {
    abstract fun planetDao(): PlanetDao
}