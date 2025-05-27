package com.chelo.appquehayencasa.ui.features.models

import androidx.compose.ui.graphics.Color
import com.chelo.appquehayencasa.ui.theme.AlmacenCategory
import com.chelo.appquehayencasa.ui.theme.CleanCategory
import com.chelo.appquehayencasa.ui.theme.MeatCategory

data class Category(val name: String, val color: Color, var isSelected: Boolean = false)


val categories = listOf<Category>(
    Category("Almacen", AlmacenCategory),
    Category("Heladera", MeatCategory),
    Category("Limpieza", CleanCategory)
)


