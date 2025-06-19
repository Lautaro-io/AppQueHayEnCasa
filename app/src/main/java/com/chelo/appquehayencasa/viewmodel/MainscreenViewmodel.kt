package com.chelo.appquehayencasa.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chelo.appquehayencasa.data.repository.CategoryRepository
import com.chelo.appquehayencasa.data.repository.ProductRepository
import com.chelo.appquehayencasa.ui.features.models.Category
import com.chelo.appquehayencasa.ui.theme.AllCategory
import com.chelo.appquehayencasa.ui.theme.DefaultCategory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject


@HiltViewModel
class MainscreenViewmodel @Inject constructor(
    private val proRepo: ProductRepository,
    private val catRepo: CategoryRepository,
) : ViewModel() {

    private val _filterActive = MutableStateFlow<Boolean>(false)
    val filterActive = _filterActive.asStateFlow()

    private val _selectedCategory = MutableStateFlow<String>("Todos")
    val selectedCategory = _selectedCategory


    private val allProducts = proRepo.getAllProducts()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(2000), emptyList())


    @OptIn(ExperimentalCoroutinesApi::class)
    private val filteredProducts = selectedCategory.flatMapLatest {
        proRepo.getCategoryProducts(it)
    }.stateIn(viewModelScope , SharingStarted.WhileSubscribed(1000) , emptyList())


    val products = combine(allProducts, filteredProducts, filterActive) { ap, fp, fa ->
        if (fa) fp else ap
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(2000),emptyList())

    val categories = catRepo.getAllCategories().map {
        it.map {

            Category(
                it.name,
                color = if (it.name == "todos") AllCategory else DefaultCategory
            )

        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(3000), emptyList())


    fun selectCategory(category: String) {
        _selectedCategory.value = category
    }

    fun changeFilterState(value: Boolean) {
        _filterActive.value = value
    }

}






















