package com.example.pizzaplanets.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.pizzaplanets.ui.screen.home.HomeScreen
import com.example.pizzaplanets.ui.screen.planet.PlanetDetailScreen
import kotlinx.serialization.Serializable

@Serializable
object HomeScreenRoute

@Serializable
data class PlanetDetailRoute(
    val planetId: Int,
)

@Composable
fun PizzaPlanetsNavHost(
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navHostController,
        startDestination = HomeScreenRoute,
        modifier = modifier,
    ) {
        composable<HomeScreenRoute> {
            HomeScreen() { planetId ->
                // navigate to planet detail screen
                navHostController.navigate(route = PlanetDetailRoute(planetId))
            }
        }
        composable<PlanetDetailRoute> { backstackEntry ->
            val route: PlanetDetailRoute = backstackEntry.toRoute()
            PlanetDetailScreen(
                planetId = route.planetId,
                navigateBack = {
                    navHostController.navigateUp()
                }
            )
        }
    }
}