package com.example.pizzaplanets.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index

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