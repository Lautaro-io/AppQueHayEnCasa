package com.chelo.appquehayencasa.data.db

import androidx.room.RoomDatabase
import com.chelo.appquehayencasa.data.daos.ProductDao
import com.chelo.appquehayencasa.data.daos.UserDao

abstract class AppDb : RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun productDao(): ProductDao

}