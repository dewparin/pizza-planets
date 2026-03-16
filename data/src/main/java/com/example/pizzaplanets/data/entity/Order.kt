package com.example.pizzaplanets.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "orders")
data class Order(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "planet_id")
    val planetId: Int,

    @ColumnInfo(name = "order_status")
    val orderStatus: OrderStatus = OrderStatus.PENDING,
)

enum class OrderStatus(val value: Int) {
    PENDING(0),
    CONFIRMED(1),
    COOKING(2),
    DELIVERING(3),
    COMPLETED(4),
    CANCELLED(5),
    ;

    companion object {
        fun fromValue(value: Int) = entries.firstOrNull { it.value == value } ?: OrderStatus.PENDING
    }
}
