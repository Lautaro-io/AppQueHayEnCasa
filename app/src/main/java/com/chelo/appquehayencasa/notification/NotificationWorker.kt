package com.chelo.appquehayencasa.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.chelo.appquehayencasa.MainActivity
import com.chelo.appquehayencasa.R
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@RequiresApi(Build.VERSION_CODES.O)


@HiltWorker
class NotificationWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    private val checker: CheckExpireDateToNotifyUseCase,
    private val scheduler: ScheduleNotification,
) : CoroutineWorker(context, workerParams) {
    init {
        Log.i("CHELO", "worker instanciado")
    }

    override suspend fun doWork(): Result {
        createNotificationChannel()

        when {
            checker.checkExpire() ->
                showNotification()

            checker.isExpired() ->
                showExpiredNotification()

        }

        scheduler.scheduleNotification()
        return Result.success()



//        if (checker.checkExpire()) {
//            showNotification()
//            scheduler.scheduleNotification()
//            return Result.success()
//        } else {
//            return Result.failure()
//        }
    }

    companion object {
        const val NOTIFICATION_CHANNEL = "NotificationChannel2"
    }

    private fun showNotification() {
        val intent = Intent(applicationContext, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            applicationContext,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notification = NotificationCompat.Builder(applicationContext, NOTIFICATION_CHANNEL)
            .setContentTitle("Producto en riesgo!")
            .setContentText("Tienes un producto pronto a vencer")
            .setPriority(NotificationManager.IMPORTANCE_HIGH)
            .setSmallIcon(R.drawable.appiconbwsvg)
            .setContentIntent(pendingIntent)
            .build()
        notificationManager.notify(0, notification)
    }

    private fun showExpiredNotification() {
        val intent = Intent(applicationContext, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            applicationContext,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notification = NotificationCompat.Builder(applicationContext, NOTIFICATION_CHANNEL)
            .setContentTitle("Producto vencido!")
            .setContentText("Tienes un producto vencido!")
            .setPriority(NotificationManager.IMPORTANCE_HIGH)
            .setSmallIcon(R.drawable.appiconbwsvg)
            .setContentIntent(pendingIntent)
            .build()
        notificationManager.notify(0, notification)
    }


    private fun createNotificationChannel() {
        val notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                NOTIFICATION_CHANNEL, "SuperChannel",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Canal de notificaciones por vencimiento de producto."
            }
            notificationManager.createNotificationChannel(channel)
        }


    }


}