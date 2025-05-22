package com.chelo.appquehayencasa.viewmodel

import androidx.lifecycle.ViewModel
import com.chelo.appquehayencasa.data.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class ProductViewmodel @Inject constructor(val repo : ProductRepository) : ViewModel() {


    val allProducts = repo.getAllProducts()

}