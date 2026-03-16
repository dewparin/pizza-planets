package com.example.pizzaplanets.di

import com.example.pizzaplanets.data.di.dataModule
import com.example.pizzaplanets.food.di.foodModule
import org.koin.dsl.module

val appModule = module {

    // Core Data Module
    includes(dataModule)

    // Food Module
    includes(foodModule)
}