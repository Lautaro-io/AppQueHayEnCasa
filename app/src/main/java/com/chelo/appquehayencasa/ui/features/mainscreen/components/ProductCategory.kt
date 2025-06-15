package com.chelo.appquehayencasa.ui.features.mainscreen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chelo.appquehayencasa.ui.features.models.Category
import com.chelo.appquehayencasa.ui.theme.ColorText
import com.chelo.appquehayencasa.ui.theme.Transparent

@Composable
fun ProductCategory(
    categoryList: List<Category>,
    selectedCategory: String,
    onItemSelected: (String) -> Unit,
    onAddClickButton: () -> Unit,
    onLongPressed: (String) -> Unit,
) {

    Row {
        LazyRow(
            Modifier
                .background(Transparent)
                .fillMaxWidth()
                .padding(top = 15.dp, bottom = 15.dp, start = 25.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            itemsIndexed(categoryList) { index, item ->
                var isSelected = item.name == selectedCategory
                CategoryItem(
                    item,
                    isSelected,
                    onClick = { onItemSelected(categoryList[index].name) },
                    onLongPressed = {
                        onLongPressed(item.name)
                    })

            }
            item {
                OutlinedButton(
                    onClick = { onAddClickButton() },
                    colors = ButtonDefaults.buttonColors(
                        contentColor = ColorText,
                        containerColor = Transparent
                    ),
                    border = BorderStroke(1.dp, ColorText),
                    modifier = Modifier.padding(horizontal = 4.dp)
                ) {
                    Icon(
                        Icons.Default.Add,
                        contentDescription = ""
                    )
                }
            }

        }


    }
}
