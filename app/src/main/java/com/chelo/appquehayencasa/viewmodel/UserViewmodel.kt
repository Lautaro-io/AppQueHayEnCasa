package com.chelo.appquehayencasa.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chelo.appquehayencasa.data.entities.UserEntity
import com.chelo.appquehayencasa.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UserViewmodel @Inject constructor(val repo: UserRepository) : ViewModel() {

    fun deleteUser(user: UserEntity) {
        viewModelScope.launch {
            repo.deleteUser(user)
        }
    }

    fun insertUser(user: UserEntity) {
        viewModelScope.launch {
            repo.insertUser(user)
        }
    }
}