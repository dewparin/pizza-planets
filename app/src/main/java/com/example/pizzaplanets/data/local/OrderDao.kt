package com.example.pizzaplanets.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.pizzaplanets.entity.OrderWithPizzaList

@Dao
interface OrderDao {
    @Transaction
    @Query(
        """
            SELECT * from orders
        """
    )
    fun queryOrderList(): List<OrderWithPizzaList>
}