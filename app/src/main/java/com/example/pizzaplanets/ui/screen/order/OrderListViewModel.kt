package com.example.pizzaplanets.ui.screen.order

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pizzaplanets.core.data.OrderRepository
import com.example.pizzaplanets.core.data.entity.result.OrderQueryResult
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

data class OrderListUiState(
    val orderList: List<OrderQueryResult> = emptyList()
)

class OrderListViewModel(
    orderRepository: OrderRepository,
) : ViewModel() {

    val uiState: StateFlow<OrderListUiState> = orderRepository
        .getAllOrders()
        .map {
            OrderListUiState(orderList = it)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = OrderListUiState(),
        )
}