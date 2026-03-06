package com.example.pizzaplanets.ui.screen.planet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pizzaplanets.data.PlanetRepository
import com.example.pizzaplanets.entity.Pizza
import com.example.pizzaplanets.entity.Planet
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

data class PlanetDetailUiState(
    val planet: Planet? = null,
    val pizzaList: List<Pizza>? = null,
)

class PlanetDetailViewModel(
    private val planetId: Int,
    private val planetRepository: PlanetRepository,
) : ViewModel() {

    val uiState: StateFlow<PlanetDetailUiState> = planetRepository
        .getPlanetWithPizzaList(planetId)
        .map {
            PlanetDetailUiState(
                planet = it?.planet,
                pizzaList = it?.pizzaList,
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = PlanetDetailUiState(),
        )
}