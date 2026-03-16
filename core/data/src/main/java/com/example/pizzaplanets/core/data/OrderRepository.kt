package com.example.pizzaplanets.core.data

import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.example.pizzaplanets.core.data.KEY_ORDER_ID
import com.example.pizzaplanets.core.data.local.OrderDao
import com.example.pizzaplanets.core.data.worker.CompleteOrderWorker
import com.example.pizzaplanets.core.data.worker.ConfirmOrderWorker
import com.example.pizzaplanets.core.data.worker.CookingOrderWorker
import com.example.pizzaplanets.core.data.worker.DeliverOrderWorker
import com.example.pizzaplanets.core.data.entity.result.OrderQueryResult
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
        val workInput = workDataOf(KEY_ORDER_ID to orderId)
        val workId = "ORDER#$orderId"
        workManager
            .beginUniqueWork(
                uniqueWorkName = workId,
                existingWorkPolicy = ExistingWorkPolicy.REPLACE,
                request = OneTimeWorkRequestBuilder<ConfirmOrderWorker>()
                    .addTag(workId)
                    .setInputData(workInput)
                    .build()
            ).then(
                OneTimeWorkRequestBuilder<CookingOrderWorker>()
                    .addTag(workId)
                    .setInputData(workInput)
                    .build()
            ).then(
                OneTimeWorkRequestBuilder<DeliverOrderWorker>()
                    .addTag(workId)
                    .setInputData(workInput)
                    .build()
            ).then(
                OneTimeWorkRequestBuilder<CompleteOrderWorker>()
                    .addTag(workId)
                    .setInputData(workInput)
                    .build()
            ).apply {
                enqueue()
            }

        return orderId
    }

}