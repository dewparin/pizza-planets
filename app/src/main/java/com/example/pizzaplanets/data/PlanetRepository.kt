package com.example.pizzaplanets.data

import android.util.Log
import com.example.pizzaplanets.data.local.PlanetDao
import com.example.pizzaplanets.entity.Planet
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import java.io.IOException

interface PlanetRepository {
    fun getAllPlanets(): Flow<List<Planet>>
    fun getPlanet(planetId: Int): Flow<Planet?>
}

class OfflinePlanetRepository(
    private val planetDao: PlanetDao,
) : PlanetRepository {

    companion object {
        const val TAG = "OfflinePlanetRepository"
    }

    override fun getAllPlanets(): Flow<List<Planet>> = planetDao
        .queryAllPlanets()
        .catch {
            if (it is IOException) {
                Log.e(TAG, "getAllPlanets # Error reading planet list")
                emit(listOf())
            } else {
                throw it
            }
        }

    override fun getPlanet(planetId: Int): Flow<Planet?> = planetDao
        .queryPlanet(planetId)
}