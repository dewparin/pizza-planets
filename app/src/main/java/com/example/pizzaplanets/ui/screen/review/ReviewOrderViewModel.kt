package com.example.pizzaplanets.ui.screen.review

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pizzaplanets.core.data.OrderRepository
import com.example.pizzaplanets.core.data.PlanetRepository
import com.example.pizzaplanets.core.data.entity.Order
import com.example.pizzaplanets.core.data.entity.Pizza
import com.example.pizzaplanets.core.data.entity.Planet
import com.example.pizzaplanets.core.data.entity.result.OrderQueryResult
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class ReviewOrderUiState(
    val planet: Planet? = null,
    val pizzaList: List<Pizza>? = null,
)

class ReviewOrderViewModel(
    planetId: Int,
    selectedPizzaIds: List<Int>,
    planetRepository: PlanetRepository,
    private val orderRepository: OrderRepository,
) : ViewModel() {

    val uiState: StateFlow<ReviewOrderUiState> = planetRepository
        .getPlanetWithSelectedPizzaList(
            planetId = planetId,
            selectedPizzaIds = selectedPizzaIds,
        ).map {
            ReviewOrderUiState(
                planet = it?.planet,
                pizzaList = it?.pizzaList,
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = ReviewOrderUiState(),
        )


    fun createOrder() {
        val planet = uiState.value.planet
        val pizzaList = uiState.value.pizzaList
        checkNotNull(planet) {
            "Cannot create the order, no planetary data"
        }
        checkNotNull(pizzaList) {
            "Cannot create the order, no menu data"
        }
        require(pizzaList.isNotEmpty()) {
            "Cannot create the order, no menu data"
        }
        viewModelScope.launch {
            val orderDetail = OrderQueryResult(
                orderInfo = Order(
                    planetId = planet.id
                ),
                planet = planet,
                pizzaList = pizzaList,
            )
            orderRepository.createOrder(orderDetail)
        }
    }
}