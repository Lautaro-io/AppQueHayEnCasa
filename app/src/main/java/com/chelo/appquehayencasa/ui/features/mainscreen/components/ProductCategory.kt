package com.chelo.appquehayencasa.ui.features.mainscreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chelo.appquehayencasa.ui.features.models.Category
import com.chelo.appquehayencasa.ui.theme.AllCategory
import com.chelo.appquehayencasa.ui.theme.AlmacenCategory
import com.chelo.appquehayencasa.ui.theme.CleanCategory
import com.chelo.appquehayencasa.ui.theme.MeatCategory
import com.chelo.appquehayencasa.ui.theme.SubcolorText
import com.chelo.appquehayencasa.ui.theme.Transparent

@Composable
fun ProductCategory(onItemSelected: (String) -> Unit) {
    val categoryList = remember {
        mutableStateListOf<Category>(
            Category("Todos", AllCategory),
            Category("Almacen", AlmacenCategory),
            Category("Heladera", MeatCategory),
            Category("Limpieza", CleanCategory),
            Category("Otros", SubcolorText)
        )
    }
    LazyRow(
        Modifier
            .background(Transparent)
            .fillMaxWidth()
            .padding(top = 15.dp, bottom = 15.dp, start = 25.dp)
    ) {
        itemsIndexed(categoryList) { index, item ->
            categoryList.forEach { category->
                if( category.name == "Todos"){
                    category.isSelected.value = true
                }
            }
            CategoryItem(item) {
                categoryList.forEach {
                    it.isSelected.value = false
                }
                categoryList[index].isSelected.value = true
                onItemSelected(categoryList[index].name)
            }
        }
    }
}
