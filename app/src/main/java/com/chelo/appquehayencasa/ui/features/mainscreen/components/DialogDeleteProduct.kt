package com.chelo.appquehayencasa.ui.features.mainscreen.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.chelo.appquehayencasa.ui.theme.ColorText
import com.chelo.appquehayencasa.ui.theme.MeatCategory
import com.chelo.appquehayencasa.ui.theme.defaulImage


@Composable
fun DialogDeleteProduct(
    onDismissClick: () -> Unit,
    onConfirmButton: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismissClick,
        confirmButton = {
            TextButton(onClick = onConfirmButton) { Text("Confirmar", color = MeatCategory) }
        },
        dismissButton = {
            TextButton(onClick = onDismissClick) {
                Text("Cancelar", color = ColorText)
            }
        },
        text = {Text("Desea elimiar este producto?")},
        title = {Text("Eliminar producto")},
        shape = RoundedCornerShape(32.dp),
        backgroundColor = defaulImage

    )

}