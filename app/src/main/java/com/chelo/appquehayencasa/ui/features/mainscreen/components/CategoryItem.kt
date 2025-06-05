package com.chelo.appquehayencasa.ui.features.mainscreen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
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


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CategoryItem(
    category: Category,
    isSelected: Boolean,
    onClick: () -> Unit,
    onLongPressed: () -> Unit,
) {

    val bgSelected = when (category.name) {
        "Almacen" -> AlmacenCategory
        "Limpieza" -> CleanCategory
        "Heladera" -> MeatCategory
        "Todos" -> AllCategory
        else -> DefaultCategory
    }

    Surface(
        border = BorderStroke(
            1.dp, bgSelected
        ),
        shape = RoundedCornerShape(16.dp),
        contentColor = ColorText,
        color = if (isSelected) bgSelected else Transparent,
        modifier = Modifier
            .padding(horizontal = 4.dp)
            .combinedClickable(
                onClick = { onClick()},
                onLongClick = {onLongPressed()}
            )
    ) {
        Row ( verticalAlignment = Alignment.CenterVertically , horizontalArrangement = Arrangement.Center) {
            Text(
                category.name,
                color = if (bgSelected == DefaultCategory && isSelected) BlackText else ColorText,
                modifier = Modifier.padding(8.dp)
            )
            if (isSelected && category.name != "Todos")
                Icon(Icons.Default.Close, contentDescription = null, tint = BlackText)
        }

    }
}