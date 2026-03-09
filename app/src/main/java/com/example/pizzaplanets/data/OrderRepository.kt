package com.example.pizzaplanets.data

import com.example.pizzaplanets.data.local.OrderDao
import com.example.pizzaplanets.entity.complex.OrderDetail
import kotlinx.coroutines.flow.Flow

interface OrderRepository {
    fun getAllOrders(): Flow<List<OrderDetail>>
    suspend fun createOrder(orderDetail: OrderDetail): Int
}

class OfflineOrderRepository(
    private val orderDao: OrderDao,
) : OrderRepository {

    override fun getAllOrders(): Flow<List<OrderDetail>> = orderDao.queryOrderList()

    override suspend fun createOrder(orderDetail: OrderDetail): Int =
        orderDao.createOrder(orderDetail)
}