package com.chelo.appquehayencasa.ui.features.mainscreen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chelo.appquehayencasa.ui.theme.BackgroundColor
import com.chelo.appquehayencasa.ui.theme.BlackText
import com.chelo.appquehayencasa.ui.theme.ButtonColor
import com.chelo.appquehayencasa.ui.theme.ColorText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryDialog(
    onDismissClick: () -> Unit,
    onConfirmButton: (String) -> Unit,
) {
    var name by remember { mutableStateOf("") }
    AlertDialog(
        onDismissRequest = { onDismissClick() }
    ) {
        Surface(
            modifier = Modifier.padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            tonalElevation = 8.dp,
            color = BackgroundColor,
            contentColor = ColorText
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Agregar nueva categoria.", fontWeight = FontWeight.Light, color = ColorText , fontSize = 16.sp)
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    placeholder = { Text("Nombre de la categoria.") },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = BlackText,
                        unfocusedContainerColor = ColorText,
                        focusedContainerColor = ColorText
                    ),
                    shape = RoundedCornerShape(32.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = { onConfirmButton(name) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ButtonColor,
                        contentColor = ColorText
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) { Text("Agregar") }

            }

        }
    }

}