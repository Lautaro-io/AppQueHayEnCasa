package com.chelo.appquehayencasa.notification

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.chelo.appquehayencasa.data.repository.ProductRepository
import kotlinx.coroutines.flow.first
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)

class CheckExpireDateToNotifyUseCase @Inject constructor(private val repo: ProductRepository) {

    suspend fun checkExpire(): Boolean {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val today = LocalDate.now()

        val products = repo.getAllProducts().first()

        val result = products.any {
            val date = LocalDate.parse(it.expireDate, formatter)
            Log.i("CHELO", "fecha producto $date")
            val should = ChronoUnit.DAYS.between(today, date) in 0..15
            Log.i("CHELO", " notificar? $should")
            should
        }

        Log.i("CHELO", "Resultado : $result")
        return result
    }
}


//.any { product ->
//    product.expireDate?.let {
//        val date = LocalDate.parse(it, formatter)
//        ChronoUnit.DAYS.between(today, date) in 0..15
//    } ?: false