package com.chelo.appquehayencasa.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.chelo.appquehayencasa.data.daos.ProductDao
import com.chelo.appquehayencasa.data.daos.UserDao
import com.chelo.appquehayencasa.data.entities.ProductEntity
import com.chelo.appquehayencasa.data.entities.UserEntity


@Database(entities = [ProductEntity::class, UserEntity::class], version = 1)
abstract class AppDb : RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun productDao(): ProductDao

}