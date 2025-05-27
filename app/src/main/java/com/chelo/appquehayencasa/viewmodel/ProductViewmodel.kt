package com.chelo.appquehayencasa.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chelo.appquehayencasa.data.entities.ProductEntity
import com.chelo.appquehayencasa.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProductViewmodel @Inject constructor(val repo : ProductRepository) : ViewModel() {


    val allProducts = repo.getAllProducts()


    fun insertProduct(product: ProductEntity){
        viewModelScope.launch {
            repo.insertProduct(product)
        }
    }

}