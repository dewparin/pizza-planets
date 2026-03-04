package com.example.pizzaplanets.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.pizzaplanets.ui.navigation.PizzaPlanetsNavHost

@Composable
fun PizzaPlanetsApp(navHostController: NavHostController = rememberNavController()) {
    PizzaPlanetsNavHost(navHostController = navHostController)
}