package com.chelo.appquehayencasa.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.chelo.appquehayencasa.data.entities.CategoryEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface CategoryDao {

    @Insert
    suspend fun insertCategory(category: CategoryEntity)

    @Delete
    suspend fun deleteCategory(category: CategoryEntity)


    @Query("SELECT * FROM categories")
    fun getAllCategories(): Flow<List<CategoryEntity>>
}