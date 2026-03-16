package com.example.pizzaplanets.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.pizzaplanets.food.screen.home.HomeScreen
import com.example.pizzaplanets.food.screen.order.OrderListScreen
import com.example.pizzaplanets.food.screen.planet.PlanetDetailScreen
import com.example.pizzaplanets.food.screen.review.ReviewOrderScreen
import kotlinx.serialization.Serializable

@Serializable
object HomeScreenRoute

@Serializable
data class PlanetDetailRoute(
    val planetId: Int,
)

@Serializable
data class ReviewOrderRoute(
    val planetId: Int,
    val selectedPizzaIds: List<Int>,
)

@Serializable
object OrderListRoute

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
            HomeScreen(
                navigateToPlanetDetail = { planetId ->
                    navHostController.navigate(route = PlanetDetailRoute(planetId))
                },
                navigateToOrderListScreen = {
                    navHostController.navigate(route = OrderListRoute)
                }
            )
        }
        composable<PlanetDetailRoute> { backstackEntry ->
            val route: PlanetDetailRoute = backstackEntry.toRoute()
            PlanetDetailScreen(
                planetId = route.planetId,
                navigateBack = {
                    navHostController.navigateUp()
                },
                navigateToReviewOrder = { planetId, selectedPizzaIds ->
                    navHostController.navigate(
                        route = ReviewOrderRoute(
                            planetId = planetId,
                            selectedPizzaIds = selectedPizzaIds,
                        )
                    )
                }
            )
        }
        composable<ReviewOrderRoute> { backstackEntry ->
            val route: ReviewOrderRoute = backstackEntry.toRoute()
            ReviewOrderScreen(
                planetId = route.planetId,
                selectedPizzaIds = route.selectedPizzaIds,
                navigateBack = {
                    navHostController.navigateUp()
                },
                navigateToOrderListScreen = {
                    navHostController.navigate(route = OrderListRoute) {
                        popUpTo<HomeScreenRoute>()
                    }
                }
            )
        }
        composable<OrderListRoute> {
            OrderListScreen(
                navigateBack = {
                    navHostController.navigateUp()
                },
            )
        }
    }
}