package com.chelo.appquehayencasa.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import com.chelo.appquehayencasa.data.entities.UserEntity


@Dao
interface UserDao {

    @Insert
    suspend fun insertUser(user: UserEntity)

    @Delete
    fun deleteUser(user: UserEntity)
}