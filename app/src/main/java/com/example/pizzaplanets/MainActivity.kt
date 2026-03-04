package com.example.pizzaplanets

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.pizzaplanets.ui.PizzaPlanetsApp
import com.example.pizzaplanets.ui.theme.PizzaPlanetsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PizzaPlanetsTheme {
                PizzaPlanetsApp()
            }
        }
    }
}
