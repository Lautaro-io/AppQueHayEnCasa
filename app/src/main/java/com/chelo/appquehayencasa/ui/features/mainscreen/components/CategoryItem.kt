package com.chelo.appquehayencasa.ui.features.mainscreen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chelo.appquehayencasa.ui.features.models.Category
import com.chelo.appquehayencasa.ui.theme.AllCategory
import com.chelo.appquehayencasa.ui.theme.AlmacenCategory
import com.chelo.appquehayencasa.ui.theme.BlackText
import com.chelo.appquehayencasa.ui.theme.CleanCategory
import com.chelo.appquehayencasa.ui.theme.ColorText
import com.chelo.appquehayencasa.ui.theme.DefaultCategory
import com.chelo.appquehayencasa.ui.theme.MeatCategory
import com.chelo.appquehayencasa.ui.theme.Transparent


@Composable
fun CategoryItem(category: Category, isSelected: Boolean, onClick: () -> Unit) {

    val bgSelected = when (category.name) {
        "Almacen" -> AlmacenCategory
        "Limpieza" -> CleanCategory
        "Heladera" -> MeatCategory
        "Todos" -> AllCategory
        else -> DefaultCategory
    }
    OutlinedButton(
        onClick = {
            onClick()
        },
        colors = ButtonDefaults.buttonColors(
            contentColor = ColorText,
            containerColor = if (isSelected) bgSelected else Transparent
        ),
        border = BorderStroke(
            1.dp,bgSelected
        ),
        modifier = Modifier.padding(horizontal = 4.dp)
    ) {
        Text(category.name, color =if (bgSelected == DefaultCategory && isSelected ) BlackText else ColorText)
        if (isSelected && category.name != "Todos")
            Icon(Icons.Default.Close, contentDescription = null, tint = BlackText)
    }
}