package com.ugurtansal.work_manager

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.work.Constraints
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.ugurtansal.work_manager.databinding.ActivityMainBinding
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)


        binding.btnBackground.setOnClickListener {

            /*val processConstraint= Constraints.Builder() //You can set constraints for the work.For example network, battery, etc.
                .setRequiredNetworkType(androidx.work.NetworkType.CONNECTED) //Network constraint
                .build()

            val request= OneTimeWorkRequestBuilder<MyWorker>()
                .setInitialDelay(10, TimeUnit.SECONDS) //After 10 seconds start the work
                .setConstraints(processConstraint)
                .build()

            WorkManager.getInstance(this).enqueue(request)

            WorkManager.getInstance(this).getWorkInfoByIdLiveData(request.id)
                .observe(this) {
                    val status=it.state.name
                    Log.e("Background Result",status)
                }

             */

            //Notification

            val request= PeriodicWorkRequestBuilder<MyWorkerNotification>(15,TimeUnit.MINUTES)//Perodic work request 15 minutes, it's minimum time
                .setInitialDelay(10,TimeUnit.SECONDS)
                .build()

            WorkManager.getInstance(this).enqueue(request)
        }

    }
}