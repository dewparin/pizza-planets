package com.example.pizzaplanets.entity.result

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.pizzaplanets.entity.Order
import com.example.pizzaplanets.entity.OrderPizzaCrossRef
import com.example.pizzaplanets.entity.Pizza
import com.example.pizzaplanets.entity.Planet

data class OrderQueryResult(
    @Embedded val order: Order,

    @Relation(
        parentColumn = "planet_id",
        entityColumn = "id",
    )
    val planet: Planet,

    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = OrderPizzaCrossRef::class,
            parentColumn = "order_id",
            entityColumn = "pizza_id",
        ),
    )
    val pizzaList: List<Pizza>,
)