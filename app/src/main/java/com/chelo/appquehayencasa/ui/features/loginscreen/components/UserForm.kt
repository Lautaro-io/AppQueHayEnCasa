package com.chelo.appquehayencasa.ui.features.loginscreen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chelo.appquehayencasa.ui.theme.BlackText
import com.chelo.appquehayencasa.ui.theme.ButtonColor
import com.chelo.appquehayencasa.ui.theme.ColorText

@Composable
fun UserForm(
    name: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
    onClickButton: () -> Unit,
) {

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Bienvenido!",
            fontWeight = FontWeight.ExtraBold,
            fontSize = 24.sp,
            color = ColorText,
            modifier = Modifier.padding(16.dp)
        )
        OutlinedTextField(
            modifier = Modifier.padding(16.dp),
            value = name,
            onValueChange = { onValueChange(it) },
            placeholder = { Text("Nombre de usuario") },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = ButtonColor,
                unfocusedContainerColor = ColorText,
                focusedContainerColor = ColorText
                , focusedTextColor = BlackText
            ),
            shape = RoundedCornerShape(32.dp)
        )
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 60.dp, vertical = 16.dp),
            onClick = onClickButton,
            colors = ButtonDefaults.buttonColors(
                contentColor = ColorText,
                containerColor = ButtonColor
            ),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 8.dp,
                pressedElevation = 16.dp
            )
        ) { Text("Crear usuario", fontWeight = FontWeight.ExtraBold, fontSize = 18.sp) }
    }

}