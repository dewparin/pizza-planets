package com.example.pizzaplanets.data.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.pizzaplanets.data.DELIVERY_ORDER_DELAY_MILLIS
import com.example.pizzaplanets.data.KEY_ORDER_ID
import com.example.pizzaplanets.data.local.OrderDao
import com.example.pizzaplanets.data.entity.OrderStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext

private const val TAG = "DeliverOrderWorker"

class DeliverOrderWorker(
    ctx: Context,
    params: WorkerParameters,
    private val orderDao: OrderDao,
) : CoroutineWorker(ctx, params) {

    override suspend fun doWork(): Result {
        return withContext(Dispatchers.IO) {
            return@withContext try {
                // update order status to DELIVERING
                val orderId = inputData.getInt(KEY_ORDER_ID, -1)
                require(orderId != -1) { "Invalid input Order ID: $orderId" }
                val order = orderDao.queryOrder(orderId).first()
                require(order != null) { "No order with id: $orderId" }
                orderDao.updateOrder(
                    order.copy(orderStatus = OrderStatus.DELIVERING)
                )

                // simulate food delivering
                delay(DELIVERY_ORDER_DELAY_MILLIS)
                Result.success()
            } catch (throwable: Throwable) {
                Log.e(TAG, "Error changning order status to DELIVERING", throwable)
                Result.failure()
            }
        }
    }

}