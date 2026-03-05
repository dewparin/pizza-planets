package com.example.pizzaplanets.data.local

import androidx.room.Dao
import androidx.room.Query
import com.example.pizzaplanets.entity.Planet
import kotlinx.coroutines.flow.Flow

@Dao
interface PlanetDao {

    @Query(
        """
            SELECT * FROM planets
            ORDER BY travel_duration_ms ASC
        """
    )
    fun queryAllPlanets(): Flow<List<Planet>>
}