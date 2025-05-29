package com.chelo.appquehayencasa.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chelo.appquehayencasa.data.entities.ProductEntity
import com.chelo.appquehayencasa.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProductViewmodel @Inject constructor(val repo : ProductRepository) : ViewModel() {


    val allProducts = repo.getAllProducts()

    private val _filteredProducts = MutableStateFlow<List<ProductEntity>>(emptyList())
    val filteredProducts: StateFlow<List<ProductEntity>> = _filteredProducts


    fun insertProduct(product: ProductEntity){
        viewModelScope.launch {
            repo.insertProduct(product)
        }
    }

    fun deleteProduct(product: ProductEntity){
        viewModelScope.launch {
            repo.deleteProduct(product)
        }
    }


    fun filterProductsByCategory(category: String){
        viewModelScope.launch {
            repo.getCategoryProducts(category).collect {product ->
               _filteredProducts.value = product
            }

        }
    }

}