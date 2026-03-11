package com.example.pizzaplanets.data

import android.util.Log
import com.example.pizzaplanets.data.local.PlanetDao
import com.example.pizzaplanets.entity.Pizza
import com.example.pizzaplanets.entity.Planet
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

data class PlanetWithPizzaList(
    val planet: Planet,
    val pizzaList: List<Pizza>
)

interface PlanetRepository {
    fun getAllPlanets(): Flow<List<Planet>>
    fun getPlanet(planetId: Int): Flow<Planet?>
    fun getPlanetWithPizzaList(planetId: Int): Flow<PlanetWithPizzaList?>
    fun getPlanetWithSelectedPizzaList(
        planetId: Int,
        selectedPizzaIds: List<Int>
    ): Flow<PlanetWithPizzaList?>
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

    override fun getPlanetWithPizzaList(planetId: Int): Flow<PlanetWithPizzaList?> = planetDao
        .queryPlanetWithPizzaList(planetId)
        .catch {
            if (it is IOException) {
                Log.e(TAG, "getPlanetWithPizzas # Error reading planet and menu")
                emit(emptyMap())
            } else {
                throw it
            }
        }.map { map ->
            if (map.isEmpty()) {
                null
            } else {
                val entry = map.entries.first()
                PlanetWithPizzaList(
                    planet = entry.key,
                    pizzaList = entry.value,
                )
            }
        }

    override fun getPlanetWithSelectedPizzaList(
        planetId: Int,
        selectedPizzaIds: List<Int>,
    ): Flow<PlanetWithPizzaList?> = planetDao
        .queryPlanetWithSelectedPizzaList(
            planetId = planetId,
            selectedPizzaList = selectedPizzaIds,
        ).catch {
            if (it is IOException) {
                Log.e(
                    TAG,
                    "getPlanetWithSelectedPizzaList # Error reading planet and selected menu"
                )
                emit(emptyMap())
            } else {
                throw it
            }
        }.map { map ->
            if (map.isEmpty()) {
                null
            } else {
                val entry = map.entries.first()
                PlanetWithPizzaList(
                    planet = entry.key,
                    pizzaList = entry.value,
                )
            }
        }

}