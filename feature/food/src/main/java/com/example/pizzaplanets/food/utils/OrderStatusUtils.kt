package com.example.pizzaplanets.food.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Motorcycle
import androidx.compose.material.icons.filled.SoupKitchen
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.filled.Timelapse
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.pizzaplanets.R
import com.example.pizzaplanets.data.entity.OrderStatus


/**
     * @return String Resource ID for the Status
     */
    fun OrderStatus.statusStringRes(): Int =
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
    fun OrderStatus.statusColor(): Color =
        when (this) {
            OrderStatus.CONFIRMED,
            OrderStatus.COOKING,
            OrderStatus.DELIVERING -> Color(0xFFFFF176) // Pastel Yellow

            OrderStatus.PENDING -> Color(0xFFCFD8DC)   // Pastel Blue-Gray
            OrderStatus.COMPLETED -> Color(0xFFA5D6A7) // Pastel Green
            OrderStatus.CANCELLED -> Color(0xFFEF9A9A) // Pastel Red
        }

    fun  OrderStatus.statusIcon(): ImageVector =
        when (this) {
            OrderStatus.PENDING -> Icons.Filled.Timelapse
            OrderStatus.CONFIRMED -> Icons.Filled.ThumbUp
            OrderStatus.COOKING -> Icons.Filled.SoupKitchen
            OrderStatus.DELIVERING -> Icons.Filled.Motorcycle
            OrderStatus.COMPLETED -> Icons.Filled.CheckCircle
            OrderStatus.CANCELLED -> Icons.Filled.Cancel
}