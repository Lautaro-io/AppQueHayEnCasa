package com.chelo.appquehayencasa.ui.features.mainscreen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.chelo.appquehayencasa.ui.features.models.Category
import com.chelo.appquehayencasa.ui.theme.ColorText
import com.chelo.appquehayencasa.ui.theme.Transparent


@Composable
fun CategoryItem(category: Category ) {
    var state by remember { mutableStateOf(false ) }
    var bg = if (state) category.color else Transparent
    OutlinedButton(
        onClick = {print("")},
        colors = ButtonDefaults.buttonColors(
            contentColor = ColorText,
            containerColor = bg
        ),
        border = BorderStroke(1.dp, category.color),
        modifier = Modifier.padding(horizontal =4.dp)
    ) {
        Text(category.name)
    }
}