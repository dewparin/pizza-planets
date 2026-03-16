package com.example.pizzaplanets.di

import com.example.pizzaplanets.core.data.di.dataModule
import com.example.pizzaplanets.ui.screen.home.HomeScreenViewModel
import com.example.pizzaplanets.ui.screen.order.OrderListViewModel
import com.example.pizzaplanets.ui.screen.planet.PlanetDetailViewModel
import com.example.pizzaplanets.ui.screen.review.ReviewOrderViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    // Core Data Module
    includes(dataModule)

    // ViewModel
    viewModel {
        HomeScreenViewModel(get())
    }
    viewModel { params ->
        PlanetDetailViewModel(
            planetId = params.get(),
            planetRepository = get(),
        )
    }
    viewModel { params ->
        ReviewOrderViewModel(
            planetId = params.get(),
            selectedPizzaIds = params.get(),
            planetRepository = get(),
            orderRepository = get(),
        )
    }
    viewModel {
        OrderListViewModel(
            orderRepository = get(),
        )
    }
}