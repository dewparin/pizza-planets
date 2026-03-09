package com.example.pizzaplanets.data

import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.example.pizzaplanets.KEY_ORDER_ID
import com.example.pizzaplanets.data.local.OrderDao
import com.example.pizzaplanets.data.worker.ConfirmOrderWorker
import com.example.pizzaplanets.data.worker.CookingOrderWorker
import com.example.pizzaplanets.data.worker.DeliverOrderWorker
import com.example.pizzaplanets.entity.result.OrderQueryResult
import kotlinx.coroutines.flow.Flow

interface OrderRepository {
    fun getAllOrders(): Flow<List<OrderQueryResult>>
    suspend fun createOrder(orderDetail: OrderQueryResult): Int
}

class OfflineOrderRepository(
    private val orderDao: OrderDao,
    private val workManager: WorkManager,
) : OrderRepository {

    override fun getAllOrders(): Flow<List<OrderQueryResult>> = orderDao.queryOrderList()

    override suspend fun createOrder(orderDetail: OrderQueryResult): Int {
        val orderId = orderDao.createOrder(orderDetail)
        workManager
            .beginUniqueWork(
                uniqueWorkName = "ORDER#$orderId",
                existingWorkPolicy = ExistingWorkPolicy.REPLACE,
                request = OneTimeWorkRequestBuilder<ConfirmOrderWorker>()
                    .setInputData(workDataOf(KEY_ORDER_ID to orderId))
                    .build()
            ).then(
                OneTimeWorkRequestBuilder<CookingOrderWorker>()
                    .setInputData(workDataOf(KEY_ORDER_ID to orderId))
                    .build()
            ).then(
                OneTimeWorkRequestBuilder<DeliverOrderWorker>()
                    .setInputData(workDataOf(KEY_ORDER_ID to orderId))
                    .build()
            ).apply {
                enqueue()
            }

        return orderId
    }

}