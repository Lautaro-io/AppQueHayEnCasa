package com.chelo.appquehayencasa.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.chelo.appquehayencasa.data.entities.ProductEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface ProductDao {

    @Insert
    suspend fun insertProduct(product: ProductEntity)

    @Delete
    suspend fun deleteProduct(product: ProductEntity)


    @Query("SELECT * FROM products")
    fun getAllProducts() : Flow<List<ProductEntity>>

    @Query("SELECT * FROM products WHERE category =:category")
    fun getCategoryProducts(category : String) : Flow<List<ProductEntity>>
}