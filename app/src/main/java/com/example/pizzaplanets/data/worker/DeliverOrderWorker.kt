package com.example.pizzaplanets.data.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.pizzaplanets.data.local.OrderDao

class DeliverOrderWorker(
    ctx: Context,
    params: WorkerParameters,
    private val orderDao: OrderDao,
) : CoroutineWorker(ctx, params) {

    override suspend fun doWork(): Result {
        TODO("Not yet implemented")
    }

}