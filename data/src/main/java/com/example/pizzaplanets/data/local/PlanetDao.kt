package com.example.pizzaplanets.data.local

import androidx.room.Dao
import androidx.room.Query
import com.example.pizzaplanets.data.entity.Pizza
import com.example.pizzaplanets.data.entity.Planet
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

    @Query(
        """
            SELECT * FROM planets
            WHERE id = :planetId
        """
    )
    fun queryPlanet(planetId: Int): Flow<Planet?>

    @Query(
        """
            SELECT * FROM planets
            JOIN pizzas ON planets.id = pizzas.planet_id
            WHERE planets.id = :planetId
        """
    )
    fun queryPlanetWithPizzaList(planetId: Int): Flow<Map<Planet, List<Pizza>>>

    @Query(
        """
            SELECT * FROM planets
            JOIN pizzas ON planets.id = pizzas.planet_id
            WHERE planets.id = :planetId
            AND pizzas.id IN (:selectedPizzaList)
        """
    )
    fun queryPlanetWithSelectedPizzaList(planetId: Int, selectedPizzaList: List<Int>): Flow<Map<Planet, List<Pizza>>>
}