package com.example.pizzaplanets.ui.utils

import com.example.pizzaplanets.R
import com.example.pizzaplanets.entity.Planet

/**
 * @return Drawable Resource ID of the Planet.
 * @throws IllegalArgumentException if the planet code is invalid.
 */
fun Planet.getPlanetDrawableByCode(): Int =
    when (planetCode) {
        "sat-1" -> R.drawable.moon
        else -> R.drawable.moon//throw IllegalArgumentException("Unknown Planet Code")
    }

fun Planet.durationToDisplayString() = "%,d".format(travelDurationMs / 1_000)