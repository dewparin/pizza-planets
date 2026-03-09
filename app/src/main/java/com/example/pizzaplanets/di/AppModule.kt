package com.example.pizzaplanets.di

import androidx.room.Room
import com.example.pizzaplanets.data.OfflineOrderRepository
import com.example.pizzaplanets.data.OfflinePlanetRepository
import com.example.pizzaplanets.data.OrderRepository
import com.example.pizzaplanets.data.PlanetRepository
import com.example.pizzaplanets.data.local.OrderDao
import com.example.pizzaplanets.data.local.PizzaPlanetsDatabase
import com.example.pizzaplanets.data.local.PlanetDao
import com.example.pizzaplanets.data.worker.ConfirmOrderWorker
import com.example.pizzaplanets.ui.screen.home.HomeScreenViewModel
import com.example.pizzaplanets.ui.screen.order.OrderListViewModel
import com.example.pizzaplanets.ui.screen.planet.PlanetDetailViewModel
import com.example.pizzaplanets.ui.screen.review.ReviewOrderViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.workmanager.dsl.worker
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    // Room Database and DAO
    single<PizzaPlanetsDatabase> {
        Room.databaseBuilder(
            androidContext(),
            PizzaPlanetsDatabase::class.java,
            "pizza_planets_database",
        )
            .createFromAsset("database/pizza_planets_database.db")
            .fallbackToDestructiveMigration(true)
            .build()
    }
    single<PlanetDao> {
        get<PizzaPlanetsDatabase>().planetDao()
    }
    single<OrderDao> {
        get<PizzaPlanetsDatabase>().orderDao()
    }

    // Repository
    single<PlanetRepository> {
        OfflinePlanetRepository(get())
    }
    single<OrderRepository> {
        OfflineOrderRepository(get())
    }

    // WorkManager
    worker { params ->
        ConfirmOrderWorker(
            ctx = params.get(),
            params = params.get(),
            orderDao = params.get(),
        )
    }

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