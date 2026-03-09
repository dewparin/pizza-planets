package com.example.pizzaplanets.entity

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Motorcycle
import androidx.compose.material.icons.filled.SoupKitchen
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.filled.Timelapse
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
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
            PENDING -> R.string.order_status_pending
            CONFIRMED -> R.string.order_status_confirmed
            COOKING -> R.string.order_status_cooking
            DELIVERING -> R.string.order_status_delivering
            COMPLETED -> R.string.order_status_completed
            CANCELLED -> R.string.order_status_cancelled
        }

    /**
     * @return Color of the status
     */
    fun statusColor(): Color =
        when (this) {
            CONFIRMED,
            COOKING,
            DELIVERING -> Color(0xFFFFF176) // Pastel Yellow

            PENDING -> Color(0xFFCFD8DC)   // Pastel Blue-Gray
            COMPLETED -> Color(0xFFA5D6A7) // Pastel Green
            CANCELLED -> Color(0xFFEF9A9A) // Pastel Red
        }

    fun statusIcon(): ImageVector =
        when (this) {
            PENDING -> Icons.Filled.Timelapse
            CONFIRMED -> Icons.Filled.ThumbUp
            COOKING -> Icons.Filled.SoupKitchen
            DELIVERING -> Icons.Filled.Motorcycle
            COMPLETED -> Icons.Filled.CheckCircle
            CANCELLED -> Icons.Filled.Cancel
        }
}
