package com.example.pizzaplanets.core.data.local

import androidx.room.TypeConverter
import com.example.pizzaplanets.core.data.entity.OrderStatus

class DatabaseTypeConverters {
    @TypeConverter
    fun fromOrderStatus(status: OrderStatus): Int = status.value

    @TypeConverter
    fun toOrderStatus(value: Int): OrderStatus = OrderStatus.fromValue(value)
}
