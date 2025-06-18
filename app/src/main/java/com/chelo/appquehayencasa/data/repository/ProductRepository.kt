package com.chelo.appquehayencasa.data.repository

import com.chelo.appquehayencasa.data.daos.ProductDao
import com.chelo.appquehayencasa.data.entities.ProductEntity
import javax.inject.Inject

class ProductRepository @Inject constructor(val dao: ProductDao) {

    suspend fun insertProduct(product: ProductEntity) = dao.insertProduct(product)

    suspend fun deleteProduct(product: ProductEntity) = dao.deleteProduct(product)

    suspend fun updateProduct(product : ProductEntity ) = dao.updateProduct(product)

    fun getAllProducts() = dao.getAllProducts()

    suspend fun getProductById(productId: Int) : ProductEntity = dao.getProductById(productId)

    fun getCategoryProducts(category : String ) = dao.getCategoryProducts(category)

    suspend fun deleteProductsByCategoryName(categoryName: String) = dao.deleteProductsByCategory(categoryName)

}