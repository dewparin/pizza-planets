package com.example.pizzaplanets.food.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pizzaplanets.data.PlanetRepository
import com.example.pizzaplanets.data.entity.Planet
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

data class HomeUiState(
    val planets: List<Planet> = listOf()
)

class HomeScreenViewModel(
    private val planetRepository: PlanetRepository,
) : ViewModel() {

    val uiState: StateFlow<HomeUiState> = planetRepository
        .getAllPlanets()
        .map { HomeUiState(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = HomeUiState(),
        )
}