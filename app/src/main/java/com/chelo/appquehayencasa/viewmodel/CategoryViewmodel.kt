package com.chelo.appquehayencasa.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chelo.appquehayencasa.data.entities.CategoryEntity
import com.chelo.appquehayencasa.data.repository.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CategoryViewmodel @Inject constructor(private val repo : CategoryRepository): ViewModel() {

    init {
        viewModelScope.launch {
            val firtsCategories = repo.getAllCategories().first()

            if (firtsCategories.isEmpty()){
                repo.insertCategory(CategoryEntity(name = "Todos" ))
                repo.insertCategory(CategoryEntity(name = "Almacen"))
                repo.insertCategory(CategoryEntity(name = "Limpieza"))
                repo.insertCategory(CategoryEntity(name = "Heladera"))
            }
        }
    }

    val allCategories  = repo.getAllCategories()


    fun insertCategory (category: CategoryEntity){
        viewModelScope.launch {
            repo.insertCategory(category)
        }
    }


    fun deleteCategoryByName(categoryName: String){
        viewModelScope.launch {
            repo.deleteCategoryByName(categoryName)
        }
    }
}