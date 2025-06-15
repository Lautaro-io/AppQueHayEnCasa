package com.chelo.appquehayencasa.notification

import android.content.Context
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.Calendar
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class ScheduleNotification @Inject constructor(
    @ApplicationContext private val context: Context) {

    fun scheduleNotification() {
        val dayDelay = calculateDelay()
        val dailyWorker = OneTimeWorkRequestBuilder<NotificationWorker>()
            .setInitialDelay(dayDelay, TimeUnit.MILLISECONDS)
            .build()

        WorkManager.getInstance(context)
            .enqueueUniqueWork(
                "TestOneTimeNotification",
                ExistingWorkPolicy.REPLACE,
                dailyWorker
            )
    }


    private fun calculateDelay(): Long {
        val now = Calendar.getInstance()
        val target = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 12)
            set(Calendar.MINUTE, 17)
            set(Calendar.SECOND, 0)
            if (before(now)) {
                add(Calendar.DAY_OF_YEAR, 1)
            }
        }
        return target.timeInMillis - now.timeInMillis
    }


}


//WHEY PROT TRUE MADE x 2 lbs $36.500
//