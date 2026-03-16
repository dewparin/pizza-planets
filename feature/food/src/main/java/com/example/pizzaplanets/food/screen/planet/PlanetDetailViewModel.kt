package com.example.pizzaplanets.food.screen.planet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pizzaplanets.data.PlanetRepository
import com.example.pizzaplanets.data.entity.Pizza
import com.example.pizzaplanets.data.entity.Planet
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

data class PlanetDetailUiState(
    val planet: Planet? = null,
    val pizzaList: List<Pizza>? = null,
    val selectedPizzaIds: Set<Int> = emptySet(),
)

class PlanetDetailViewModel(
    planetId: Int,
    planetRepository: PlanetRepository,
) : ViewModel() {

    private val _selectedPizzaIds = MutableStateFlow<Set<Int>>(emptySet())

    val uiState: StateFlow<PlanetDetailUiState> = planetRepository
        .getPlanetWithPizzaList(planetId)
        .combine(_selectedPizzaIds) { planetPizzaList, selectedPizzaIds ->
            PlanetDetailUiState(
                planet = planetPizzaList?.planet,
                pizzaList = planetPizzaList?.pizzaList,
                selectedPizzaIds = selectedPizzaIds,
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = PlanetDetailUiState(),
        )

    fun togglePizzaSelection(pizzaId: Int, selected: Boolean) {
        _selectedPizzaIds.value = if (selected) {
            _selectedPizzaIds.value + pizzaId
        } else {
            _selectedPizzaIds.value - pizzaId
        }
    }
}