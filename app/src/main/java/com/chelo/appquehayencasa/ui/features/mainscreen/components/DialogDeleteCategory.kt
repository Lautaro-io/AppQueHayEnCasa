package com.chelo.appquehayencasa.ui.features.mainscreen.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.chelo.appquehayencasa.ui.theme.BackgroundColor
import com.chelo.appquehayencasa.ui.theme.ColorText
import com.chelo.appquehayencasa.ui.theme.ConfirmRed

@Composable
fun DialogDeleteDialog(onConfirmButton: () -> Unit, onDismissButton: () -> Unit) {
    AlertDialog(
        onDismissRequest = { onDismissButton() },
        confirmButton = {
            TextButton(onClick = onConfirmButton) {
                Text(
                    "Eliminar",
                    color = ConfirmRed
                )
            }
        },
        dismissButton = {
            TextButton(onClick = onDismissButton) { Text("Cancelar", color = ColorText) } },
        title = { Text("Eliminar esta categoria?", color = ColorText) },
        text = { Text("Desea eliminar esta categoria?\nAl eliminar esta categoria se borran todos los productos de la misma.", color = ColorText) },
        shape = RoundedCornerShape(16.dp),
        backgroundColor = BackgroundColor,
        contentColor = ColorText,
    )
}