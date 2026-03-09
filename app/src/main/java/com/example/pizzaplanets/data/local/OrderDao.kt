package com.example.pizzaplanets.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.pizzaplanets.entity.Order
import com.example.pizzaplanets.entity.OrderPizzaCrossRef
import com.example.pizzaplanets.entity.complex.OrderDetail
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderDao {
    @Transaction
    @Query(
        """
            SELECT * from orders
        """
    )
    fun queryOrderList(): Flow<List<OrderDetail>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrder(order: Order): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrderPizzaCrossRefs(crossRefs: List<OrderPizzaCrossRef>)

    @Transaction
    suspend fun createOrder(orderDetail: OrderDetail) {
        val orderId = insertOrder(orderDetail.order).toInt()
        val crossRefs = orderDetail.pizzaList.map { pizza ->
            OrderPizzaCrossRef(orderId, pizza.id)
        }
        insertOrderPizzaCrossRefs(crossRefs)
    }
}