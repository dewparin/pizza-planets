package com.example.pizzaplanets.data.entity.result

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.pizzaplanets.data.entity.Order
import com.example.pizzaplanets.data.entity.OrderPizzaCrossRef
import com.example.pizzaplanets.data.entity.Pizza
import com.example.pizzaplanets.data.entity.Planet

data class OrderQueryResult(
    @Embedded val orderInfo: Order,

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