package com.chelo.appquehayencasa.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.chelo.appquehayencasa.data.daos.CategoryDao
import com.chelo.appquehayencasa.data.daos.ProductDao
import com.chelo.appquehayencasa.data.daos.UserDao
import com.chelo.appquehayencasa.data.entities.CategoryEntity
import com.chelo.appquehayencasa.data.entities.ProductEntity
import com.chelo.appquehayencasa.data.entities.UserEntity


@Database(entities = [ProductEntity::class, UserEntity::class, CategoryEntity::class], version = 2)
abstract class AppDb : RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun productDao(): ProductDao

    abstract fun categoryDao(): CategoryDao

}