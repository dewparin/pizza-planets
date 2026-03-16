package com.example.pizzaplanets.data.di

import androidx.room.Room
import androidx.work.WorkManager
import com.example.pizzaplanets.data.OfflineOrderRepository
import com.example.pizzaplanets.data.OfflinePlanetRepository
import com.example.pizzaplanets.data.OrderRepository
import com.example.pizzaplanets.data.PlanetRepository
import com.example.pizzaplanets.data.local.OrderDao
import com.example.pizzaplanets.data.local.PizzaPlanetsDatabase
import com.example.pizzaplanets.data.local.PlanetDao
import com.example.pizzaplanets.data.worker.CompleteOrderWorker
import com.example.pizzaplanets.data.worker.ConfirmOrderWorker
import com.example.pizzaplanets.data.worker.CookingOrderWorker
import com.example.pizzaplanets.data.worker.DeliverOrderWorker
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.workmanager.dsl.worker
import org.koin.dsl.module

val dataModule = module {

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
        OfflineOrderRepository(get(), get())
    }

    // WorkManager
    single<WorkManager> {
        WorkManager.getInstance(get())
    }
    worker {
        ConfirmOrderWorker(
            ctx = get(),
            params = get(),
            orderDao = get(),
        )
    }
    worker {
        CookingOrderWorker(
            ctx = get(),
            params = get(),
            orderDao = get(),
        )
    }
    worker {
        DeliverOrderWorker(
            ctx = get(),
            params = get(),
            orderDao = get(),
        )
    }
    worker {
        CompleteOrderWorker(
            ctx = get(),
            params = get(),
            orderDao = get(),
        )
    }
}