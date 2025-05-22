package com.chelo.appquehayencasa.data.repository

import com.chelo.appquehayencasa.data.daos.UserDao
import com.chelo.appquehayencasa.data.entities.UserEntity
import javax.inject.Inject

class UserRepository @Inject constructor(val dao: UserDao) {

    suspend fun insertUser(user: UserEntity) = dao.insertUser(user)

    fun deleteUser(user: UserEntity) = dao.deleteUser(user)
}