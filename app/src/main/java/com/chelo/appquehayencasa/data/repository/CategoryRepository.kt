package com.chelo.appquehayencasa.data.repository

import com.chelo.appquehayencasa.data.daos.CategoryDao
import com.chelo.appquehayencasa.data.entities.CategoryEntity
import javax.inject.Inject

class CategoryRepository @Inject constructor(private val dao: CategoryDao) {

    suspend fun insertCategory(category: CategoryEntity) = dao.insertCategory(category)

    suspend fun deleteCategory(category: CategoryEntity) = dao.deleteCategory(category)

    fun getAllCategories() = dao.getAllCategories()

    suspend fun deleteCategoryByName(categoryName: String) = dao.deleteCategoryByName(categoryName)


    suspend fun getCategoryByName(categoryName : String) = dao.getCategoryByName(categoryName)
}