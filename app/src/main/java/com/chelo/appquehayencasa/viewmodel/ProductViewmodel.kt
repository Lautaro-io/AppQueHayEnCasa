package com.chelo.appquehayencasa.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chelo.appquehayencasa.data.entities.ProductEntity
import com.chelo.appquehayencasa.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProductViewmodel @Inject constructor(val repo: ProductRepository) : ViewModel() {


    val allProducts = repo.getAllProducts().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(2000),
        emptyList()
    )

    private val _filteredProducts = MutableStateFlow<List<ProductEntity>>(emptyList())
    val filteredProducts: StateFlow<List<ProductEntity>> = _filteredProducts




    var name by mutableStateOf("")
        private set

    var count by mutableStateOf("")
        private set

    var category by mutableStateOf("")
        private set

    var expireDate by mutableStateOf("")
        private set


    fun onNameChanged(value: String) {
        name = value
    }

    fun onCountChanged(value: String) {
        count = value
    }

    fun onCategoryChanged(value: String) {
        category = value
    }

    fun onExpireChanged(value: String) {
        expireDate = value
    }


    fun loadProduct(productId: Long){
        viewModelScope.launch {
            val product = repo.getProductById(productId)
            name = product.nameProduct
            count = product.count.toString()
            category = product.category
            expireDate = product.expireDate
        }
    }


    fun insertProduct(product: ProductEntity) {
        viewModelScope.launch {
            repo.insertProduct(product)
        }
    }

    fun deleteProduct(product: ProductEntity) {
        viewModelScope.launch {
            repo.deleteProduct(product)
        }
    }

    fun updateProduct(product: ProductEntity) {
        viewModelScope.launch {
            repo.updateProduct(product)
        }
    }




    fun saveProduct(imagePath : String , productId : Long?){


        viewModelScope.launch {
            val product = ProductEntity(
                id = productId ?: 0,
                nameProduct = name,
                expireDate = expireDate,
                count = count.toInt(),
                category = category,
                image = imagePath
            )
            if (productId != null){
                updateProduct(product)
            }
            else{
                insertProduct(product)
            }
        }
    }


    fun filterProductsByCategory(categoryName: String) {
        viewModelScope.launch {
            repo.getCategoryProducts(categoryName).collect { product ->
                _filteredProducts.value = product
            }

        }
    }

    fun deleteProductsByCategory(categoryName: String) {
        viewModelScope.launch {
            repo.deleteProductsByCategoryName(categoryName)
        }
    }


}