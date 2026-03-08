package com.example.pizzaplanets.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import androidx.room.Junction
import androidx.room.Relation

@Entity(
    tableName = "orders_pizzas",
    primaryKeys = ["order_id", "pizza_id"],
    indices = [Index(value = ["pizza_id"])],
)
data class OrderPizzaCrossRef(
    @ColumnInfo(name = "order_id")
    val orderId: Int,

    @ColumnInfo(name = "pizza_id")
    val pizzaId: Int,
)

data class OrderWithPizzaList(
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