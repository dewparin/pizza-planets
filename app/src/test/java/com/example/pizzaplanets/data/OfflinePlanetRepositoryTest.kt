package com.example.pizzaplanets.data

import com.example.pizzaplanets.data.local.PlanetDao
import com.example.pizzaplanets.entity.Planet
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.io.IOException

class OfflinePlanetRepositoryTest {

    private val planetDao: PlanetDao = mockk()
    private val repository = OfflinePlanetRepository(planetDao)

    @Test
    fun getAllPlanets_returnsPlanetsFromDao() = runTest {
        every {
            planetDao.queryAllPlanets()
        } returns flowOf(mockPlanets)

        val result = repository
            .getAllPlanets()
            .first()

        assertEquals(mockPlanets, result)
    }

    @Test
    fun getAllPlanets_databaseThrowsIOException_returnsEmptyList() = runTest {
        every {
            planetDao.queryAllPlanets()
        } returns flow { throw IOException() }

        val result = repository
            .getAllPlanets()
            .first()

        assertEquals(emptyList<Planet>(), result)
    }


}

private val mockPlanets = listOf(
    Planet(
        id = 1,
        planetCode = "sat-1",
        name = "Moon",
        description = "Moon",
        travelDurationMs = 13_000,
    ),
    Planet(
        id = 2,
        planetCode = "pln-1",
        name = "Mercury",
        description = "Mercury",
        travelDurationMs = 13_000,
    ),
    Planet(
        id = 3,
        planetCode = "pln-2",
        name = "Venus",
        description = "Venus",
        travelDurationMs = 13_000,
    ),
)