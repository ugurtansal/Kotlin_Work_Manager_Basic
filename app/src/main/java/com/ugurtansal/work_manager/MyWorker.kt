package com.ugurtansal.work_manager

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class MyWorker(context: Context,workerParameters: WorkerParameters): Worker(context,workerParameters) {
    override fun doWork(): Result {
        val basicProcess=10+20;
        Log.e("Background Result",basicProcess.toString())

        return Result.success()
    }

}