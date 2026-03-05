package com.example.pizzaplanets.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pizzaplanets.entity.Planet

@Database(
    version = 1,
    exportSchema = false,
    entities = [Planet::class],
)
abstract class PizzaPlanetsDatabase : RoomDatabase() {
    abstract fun planetDao(): PlanetDao
}