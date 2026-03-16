package com.example.pizzaplanets.food.di

import com.example.pizzaplanets.data.di.dataModule
import com.example.pizzaplanets.food.screen.home.HomeScreenViewModel
import com.example.pizzaplanets.food.screen.order.OrderListViewModel
import com.example.pizzaplanets.food.screen.planet.PlanetDetailViewModel
import com.example.pizzaplanets.food.screen.review.ReviewOrderViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val foodModule = module {

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
