package com.example.pizzaplanets.entity

import androidx.compose.ui.graphics.Color
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pizzaplanets.R

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
        fun fromValue(value: Int) = entries.first { it.value == value }
    }

    /**
     * @return String Resource ID for the Status
     */
    fun statusStringRes(): Int =
        when (this) {
            OrderStatus.PENDING -> R.string.order_status_pending
            OrderStatus.CONFIRMED -> R.string.order_status_confirmed
            OrderStatus.COOKING -> R.string.order_status_cooking
            OrderStatus.DELIVERING -> R.string.order_status_delivering
            OrderStatus.COMPLETED -> R.string.order_status_completed
            OrderStatus.CANCELLED -> R.string.order_status_cancelled
        }

    /**
     * @return Color of the status
     */
    fun statusColor(): Color =
        when (this) {
            OrderStatus.CONFIRMED,
            OrderStatus.COOKING,
            OrderStatus.DELIVERING -> Color.Yellow

            OrderStatus.PENDING -> Color.Gray
            OrderStatus.COMPLETED -> Color.Green
            OrderStatus.CANCELLED -> Color.Red
        }
}
