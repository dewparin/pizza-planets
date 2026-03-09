package com.example.pizzaplanets.ui.screen.review

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pizzaplanets.data.OrderRepository
import com.example.pizzaplanets.data.PlanetRepository
import com.example.pizzaplanets.entity.Order
import com.example.pizzaplanets.entity.Pizza
import com.example.pizzaplanets.entity.Planet
import com.example.pizzaplanets.entity.complex.OrderDetail
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
            val orderDetail = OrderDetail(
                order = Order(
                    planetId = planet.id
                ),
                pizzaList = pizzaList
            )
            orderRepository.createOrder(orderDetail)
        }
    }
}