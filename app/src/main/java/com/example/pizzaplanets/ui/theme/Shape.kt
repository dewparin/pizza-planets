package com.example.pizzaplanets.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val Shapes = Shapes(
    small = RoundedCornerShape(50.dp),
    medium = RoundedCornerShape(
        topStart = 48.dp,
        topEnd = 0.dp,
        bottomEnd = 16.dp,
        bottomStart = 20.dp,
    ),
)
