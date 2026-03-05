package com.example.pizzaplanets.di

import androidx.room.Room
import com.example.pizzaplanets.data.OfflinePlanetRepository
import com.example.pizzaplanets.data.PlanetRepository
import com.example.pizzaplanets.data.local.PizzaPlanetsDatabase
import com.example.pizzaplanets.data.local.PlanetDao
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {

    // Room Database and DAO
    single<PizzaPlanetsDatabase> {
        Room.databaseBuilder(
            androidContext(),
            PizzaPlanetsDatabase::class.java,
            "pizza_planets_database",
        ).build()
    }
    single<PlanetDao> {
        get<PizzaPlanetsDatabase>().planetDao()
    }

    // Repository
    single<PlanetRepository> {
        OfflinePlanetRepository(get())
    }
}