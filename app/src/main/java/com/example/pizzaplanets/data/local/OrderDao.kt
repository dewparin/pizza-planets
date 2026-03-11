package com.example.pizzaplanets.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.pizzaplanets.entity.Order
import com.example.pizzaplanets.entity.OrderPizzaCrossRef
import com.example.pizzaplanets.entity.OrderStatus
import com.example.pizzaplanets.entity.result.OrderQueryResult
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderDao {
    @Transaction
    @Query(
        """
            SELECT * from orders
            ORDER BY id DESC
        """
    )
    fun queryOrderList(): Flow<List<OrderQueryResult>>

    @Query(
        """
            SELECT * FROM orders
            WHERE id = :orderId
        """
    )
    fun queryOrder(orderId: Int): Flow<Order?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrder(order: Order): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrderPizzaCrossRefs(crossRefs: List<OrderPizzaCrossRef>)

    @Transaction
    suspend fun createOrder(orderDetail: OrderQueryResult): Int {
        val orderId = insertOrder(orderDetail.orderInfo).toInt()
        val crossRefs = orderDetail.pizzaList.map { pizza ->
            OrderPizzaCrossRef(orderId, pizza.id)
        }
        insertOrderPizzaCrossRefs(crossRefs)

        return orderId
    }

    @Update
    suspend fun updateOrder(order: Order)
}