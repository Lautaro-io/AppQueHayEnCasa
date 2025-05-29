package com.chelo.appquehayencasa.ui.features.mainscreen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chelo.appquehayencasa.ui.features.models.categories
import com.chelo.appquehayencasa.ui.theme.Transparent

@Composable
fun ProductCategory(onItemSelected: (String) -> Unit) {
    LazyRow(
        Modifier
            .background(Transparent)
            .fillMaxWidth()
            .padding(top = 15.dp, bottom = 15.dp, start = 25.dp)
    ) {
        itemsIndexed(categories) { index, item ->
            CategoryItem(item){
                onItemSelected(categories[index].name)
            }
        }
    }
}