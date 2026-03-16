package com.example.pizzaplanets.ui.screen.shared

import com.example.pizzaplanets.data.entity.Planet
import com.example.pizzaplanets.data.entity.Order
import com.example.pizzaplanets.data.entity.OrderStatus
import com.example.pizzaplanets.data.entity.Pizza
import com.example.pizzaplanets.data.entity.result.OrderQueryResult

val mockPlanet = Planet(
    id = 1,
    planetCode = "sat-1",
    name = "Moon",
    description = "Welcome to our closest cosmic kitchen! Moon Base Pizzeria serves up legendary low-gravity pies where the cheese stretches all the way to the ceiling. Try our signature \"Crater Crust Supreme\" — baked in ancient volcanic vents for that extra smoky flavor. Fun fact: our dough rises 6x higher here thanks to 1/6th Earth gravity. Every bite is literally out of this world!",
    travelDurationMs = 13_000,
)
val mockPizzaList = listOf(
    Pizza(
        id = 1,
        planetId = 1,
        name = "Crater Crust Supreme",
        description = "Our legendary signature pie baked in ancient lunar volcanic vents. Loaded with smoked mozzarella, roasted garlic, and a ring of crispy crust shaped like a crater rim. The smoky flavor is literally geological.",
    ),
    Pizza(
        id = 2,
        planetId = 1,
        name = "The Dark Side Deluxe",
        description = "A mysterious half-and-half pizza — one side blazing hot with jalapeños and ghost pepper sauce, the other cool with ricotta and fresh basil. You never know which side you''ll bite into first.",
    ),
    Pizza(
        id = 3,
        planetId = 1,
        name = "Low-G Cheese Pull",
        description = "Apollo Classic', 'A tribute to the first humans who visited. Simple, timeless, and reliable — tomato sauce, fresh mozzarella, basil, and a drizzle of olive oil. One small bite for man, one giant flavor for mankind.",
    ),
)

val mockOrderDetailList = listOf(
    OrderQueryResult(
        orderInfo = Order(
            planetId = mockPlanet.id,
            orderStatus = OrderStatus.PENDING
        ),
        planet = mockPlanet,
        pizzaList = mockPizzaList,
    )
)