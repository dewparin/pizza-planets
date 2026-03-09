package com.example.pizzaplanets.data.local

import androidx.room.TypeConverter
import com.example.pizzaplanets.entity.OrderStatus

class DatabaseTypeConverters {
    @TypeConverter
    fun fromOrderStatus(status: OrderStatus): Int = status.value

    @TypeConverter
    fun toOrderStatus(value: Int): OrderStatus = OrderStatus.fromValue(value)
}
