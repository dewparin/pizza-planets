package com.example.pizzaplanets.data

import com.example.pizzaplanets.data.local.OrderDao
import com.example.pizzaplanets.entity.result.OrderQueryResult
import kotlinx.coroutines.flow.Flow

interface OrderRepository {
    fun getAllOrders(): Flow<List<OrderQueryResult>>
    suspend fun createOrder(orderDetail: OrderQueryResult): Int
}

class OfflineOrderRepository(
    private val orderDao: OrderDao,
) : OrderRepository {

    override fun getAllOrders(): Flow<List<OrderQueryResult>> = orderDao.queryOrderList()

    override suspend fun createOrder(orderDetail: OrderQueryResult): Int =
        orderDao.createOrder(orderDetail)
}