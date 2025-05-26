package com.chelo.appquehayencasa.ui.features.productform

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.chelo.appquehayencasa.ui.features.mainscreen.components.TitleSection
import com.chelo.appquehayencasa.ui.features.models.categories

import com.chelo.appquehayencasa.ui.features.productform.components.ImageContainer
import com.chelo.appquehayencasa.ui.theme.BackgroundColor
import com.chelo.appquehayencasa.ui.theme.ButtonColor
import com.chelo.appquehayencasa.ui.theme.ColorText
import java.io.File


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductForm(imagePath: String, navController: NavController) {
    var expanded by remember { mutableStateOf(false) }
    var name by remember { mutableStateOf("") }
    var count by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var expireDate by remember { mutableStateOf("") }
    val categories = categories
    val imageUri = remember { mutableStateOf<File?>(null) }

    Scaffold() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundColor)
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TitleSection("Agregar producto")
            Spacer(modifier = Modifier.height(32.dp))
            ImageContainer(imagePath, navController)
            Spacer(modifier = Modifier.height(80.dp))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    placeholder = { Text("Nombre del producto") },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = ColorText,
                        unfocusedContainerColor = ColorText
                    ),
                    shape = RoundedCornerShape(32.dp)
                )

                OutlinedTextField(
                    value = count,
                    onValueChange = { count = it },
                    placeholder = { Text("Cantidad") },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = ColorText,
                        unfocusedContainerColor = ColorText
                    ),
                    shape = RoundedCornerShape(32.dp)
                )


                ExposedDropdownMenuBox(
                    expanded,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 64.dp),
                    onExpandedChange = { expanded = !expanded }) {
                    OutlinedTextField(
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth(),
                        value = category ?: "Categoria",
                        onValueChange = { },
                        readOnly = true,
                        placeholder = { Text("Categoria") },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = ColorText,
                            unfocusedContainerColor = ColorText
                        ),
                        shape = RoundedCornerShape(32.dp)
                    )
                    ExposedDropdownMenu(expanded, onDismissRequest = { expanded = false }) {
                        categories.forEach { categoryItem ->
                            DropdownMenuItem(
                                text = { Text(categoryItem.name) },
                                onClick = { category = categoryItem.name })
                        }
                    }
                }


                OutlinedTextField(
                    value = expireDate,
                    onValueChange = { expireDate = it },
                    placeholder = { Text("Vencimiento (opcional)") },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = ColorText,
                        unfocusedContainerColor = ColorText
                    ),
                    shape = RoundedCornerShape(32.dp)
                )
            }
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 64.dp, vertical = 24.dp),
                onClick = { TODO() },
                colors = ButtonDefaults.buttonColors(
                    contentColor = ColorText,
                    containerColor = ButtonColor
                )
            ) { Text("Agregar Producto", fontWeight = FontWeight.ExtraBold, fontSize = 18.sp) }
        }
    }
}