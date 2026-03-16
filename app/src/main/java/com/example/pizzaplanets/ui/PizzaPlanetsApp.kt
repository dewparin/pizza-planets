@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.pizzaplanets.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.pizzaplanets.ui.navigation.PizzaPlanetsNavHost

@Composable
fun PizzaPlanetsApp(navHostController: NavHostController = rememberNavController()) {
    PizzaPlanetsNavHost(navHostController = navHostController)
}
