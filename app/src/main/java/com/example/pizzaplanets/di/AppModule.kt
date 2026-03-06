package com.example.pizzaplanets.di

import androidx.room.Room
import com.example.pizzaplanets.data.OfflinePlanetRepository
import com.example.pizzaplanets.data.PlanetRepository
import com.example.pizzaplanets.data.local.PizzaPlanetsDatabase
import com.example.pizzaplanets.data.local.PlanetDao
import com.example.pizzaplanets.ui.screen.home.HomeScreenViewModel
import com.example.pizzaplanets.ui.screen.planet.PlanetDetailViewModel
import org.koin.android.ext.koin.androidContext
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
            .fallbackToDestructiveMigration(true)
            .build()
    }
    single<PlanetDao> {
        get<PizzaPlanetsDatabase>().planetDao()
    }

    // Repository
    single<PlanetRepository> {
        OfflinePlanetRepository(get())
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
}