package com.example.pizzaplanets.entity.complex

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.pizzaplanets.entity.Order
import com.example.pizzaplanets.entity.OrderPizzaCrossRef
import com.example.pizzaplanets.entity.Pizza

data class OrderDetail(
    @Embedded val order: Order,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = OrderPizzaCrossRef::class,
            parentColumn = "order_id",
            entityColumn = "pizza_id",
        ),
    )
    val pizzaList: List<Pizza>
)

fun OrderDetail.planet() = order.planetId