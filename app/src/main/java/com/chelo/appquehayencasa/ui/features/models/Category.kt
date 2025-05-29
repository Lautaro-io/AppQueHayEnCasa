package com.chelo.appquehayencasa.ui.features.models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import com.chelo.appquehayencasa.ui.theme.AlmacenCategory
import com.chelo.appquehayencasa.ui.theme.CleanCategory
import com.chelo.appquehayencasa.ui.theme.MeatCategory
import com.chelo.appquehayencasa.ui.theme.SubcolorText

data class Category(val name: String, val color: Color, var isSelected: MutableState<Boolean> = mutableStateOf(false))


val categories =
    mutableStateListOf<Category>(
        Category("Almacen", AlmacenCategory),
        Category("Heladera", MeatCategory),
        Category("Limpieza", CleanCategory),
        Category("Otros", SubcolorText)

    )



