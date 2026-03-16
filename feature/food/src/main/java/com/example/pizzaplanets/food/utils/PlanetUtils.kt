package com.example.pizzaplanets.food.utils

import com.example.pizzaplanets.R
import com.example.pizzaplanets.data.entity.Planet

/**
 * @return Drawable Resource ID of the Planet.
 * @throws IllegalArgumentException if the planet code is invalid.
 */
fun Planet.getPlanetDrawableByCode(): Int =
    when (planetCode) {
        "sat-1" -> R.drawable.moon
        "pln-1" -> R.drawable.mercury
        "pln-2" -> R.drawable.venus
        "pln-4" -> R.drawable.mars
        "pln-5" -> R.drawable.jupiter
        "pln-6" -> R.drawable.saturn
        "pln-7" -> R.drawable.uranus
        "pln-8" -> R.drawable.neptune
        else -> throw IllegalArgumentException("Unknown Planet Code")
    }

fun Planet.durationToDisplayString() = "%,d".format(travelDurationMs / 1_000)