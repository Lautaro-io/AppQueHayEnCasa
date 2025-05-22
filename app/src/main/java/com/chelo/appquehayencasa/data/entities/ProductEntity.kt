package com.chelo.appquehayencasa.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey(autoGenerate = true)
    val id  : Long  = 0 ,
    val nameProduct : String,
    val expireDate : String,
    val count: Int,
    val category : String,
    val image : String

)
