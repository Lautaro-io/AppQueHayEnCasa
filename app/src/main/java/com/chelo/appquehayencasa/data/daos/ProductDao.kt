package com.chelo.appquehayencasa.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.chelo.appquehayencasa.data.entities.ProductEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface ProductDao {

    @Insert
    suspend fun insertProduct(product: ProductEntity)

    @Delete
    suspend fun deleteProduct(product: ProductEntity)

    @Update
    suspend fun updateProduct(product: ProductEntity)


    @Query("SELECT * FROM products")
    fun getAllProducts() : Flow<List<ProductEntity>>

    @Query("SELECT * FROM products WHERE category =:category")
    fun getCategoryProducts(category : String) : Flow<List<ProductEntity>>

    @Query("DELETE FROM products WHERE category =:category")
    suspend fun deleteProductsByCategory(category : String)


    @Query("SELECT * FROM products WHERE id = :productId")
    suspend fun getProductById(productId : Int) : ProductEntity
}