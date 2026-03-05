package com.example.pizzaplanets.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.pizzaplanets.ui.screen.home.HomeScreen
import kotlinx.serialization.Serializable

@Serializable
object HomeScreenRoute

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
                // TODO: navigate to planet detail screen
            }
        }
    }
}