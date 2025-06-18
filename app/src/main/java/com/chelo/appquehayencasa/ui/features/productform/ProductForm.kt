package com.chelo.appquehayencasa.ui.features.productform

import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.chelo.appquehayencasa.data.entities.ProductEntity
import com.chelo.appquehayencasa.ui.features.mainscreen.components.TitleSection
import com.chelo.appquehayencasa.ui.features.navigation.MainScreen

import com.chelo.appquehayencasa.ui.features.productform.components.ImageContainer
import com.chelo.appquehayencasa.ui.theme.BackgroundColor
import com.chelo.appquehayencasa.ui.theme.BlackText
import com.chelo.appquehayencasa.ui.theme.ButtonColor
import com.chelo.appquehayencasa.ui.theme.ColorText
import com.chelo.appquehayencasa.ui.theme.SubcolorText
import com.chelo.appquehayencasa.viewmodel.CategoryViewmodel
import com.chelo.appquehayencasa.viewmodel.ProductViewmodel
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductForm(imagePath: String, navController: NavController, productId: Int?) {

    val productViewmodel: ProductViewmodel = hiltViewModel()
    val categoryViewmodel: CategoryViewmodel = hiltViewModel()
    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }
    var name by remember { mutableStateOf("") }
    var count by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var expireDate by remember { mutableStateOf("") }
    val categories by categoryViewmodel.allCategories.collectAsState(emptyList())
    val product by produceState<ProductEntity?>(initialValue = null, productId) {
        value = productId?.let {
            productViewmodel.getProductById(it)
        }
    }
    product?.let {
        name = it.nameProduct
        count = it.count.toString()
        category = it.category
        expireDate = it.expireDate
    }
    val state = rememberDatePickerState()
    var showDateDialog by remember { mutableStateOf(false) }
    val date = state.selectedDateMillis
    date?.let {
        val localDate = Instant.ofEpochMilli(it).atZone(ZoneId.of("UTC")).toLocalDate()
        val internalFormat = localDate.format(DateTimeFormatter.ISO_LOCAL_DATE)
        expireDate = internalFormat
    }

    Scaffold {
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
                        unfocusedContainerColor = ColorText,
                        focusedTextColor = BlackText,
                        unfocusedTextColor = BlackText
                    ),
                    shape = RoundedCornerShape(32.dp)
                )

                OutlinedTextField(
                    value = count,
                    onValueChange = { count = it },
                    placeholder = { Text("Cantidad") },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = ColorText,
                        unfocusedContainerColor = ColorText,
                        focusedTextColor = BlackText,
                        unfocusedTextColor = BlackText
                    ),
                    shape = RoundedCornerShape(32.dp)
                )


                ExposedDropdownMenuBox(
                    expanded,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 60.dp),
                    onExpandedChange = { expanded = !expanded }) {
                    OutlinedTextField(
                        modifier = Modifier
                            .menuAnchor(type = MenuAnchorType.PrimaryEditable, true)
                            .fillMaxWidth(),
                        value = if (category != "") category else "Categoria",
                        onValueChange = { },
                        readOnly = true,
                        placeholder = { Text("Categoria") },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = ColorText,
                            unfocusedContainerColor = ColorText,
                            focusedTextColor = BlackText,
                            unfocusedTextColor = SubcolorText
                        ),
                        shape = RoundedCornerShape(32.dp)
                    )
                    ExposedDropdownMenu(expanded, onDismissRequest = { expanded = false }) {
                        categories.forEach { categoryItem ->
                            DropdownMenuItem(
                                text = { Text(categoryItem.name) },
                                onClick = {
                                    category = categoryItem.name
                                    expanded = false
                                })
                        }
                    }
                }


                Button(
                    onClick = { showDateDialog = true },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ColorText,
                        contentColor = BlackText
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 64.dp),

                    ) {

                    Text(
                        if (expireDate != "") "Vencimiento : $expireDate" else "Fecha de vencimiento(opcional)",
                        modifier = Modifier
                            .padding(8.dp),
                        fontWeight = FontWeight.Light
                    )


                }

            }
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 64.dp, vertical = 24.dp),
                onClick = {
                    when {
                        listOf(name, count, category, imagePath).any { it.isEmpty() } ->
                            Toast.makeText(context, "Complete todos los campos", Toast.LENGTH_SHORT)
                                .show()

                        else -> {
                            if (product != null){
                                val updatedProduct = product!!.copy(
                                    nameProduct = name,
                                    expireDate = expireDate,
                                    count = count.toInt(),
                                    category = category,
                                    image = imagePath
                                )

                                productViewmodel.updateProduct(updatedProduct)
                                navController.navigate(MainScreen.route) {
                                    popUpTo(0) { inclusive = true }
                                }

                                Toast.makeText(
                                    context,
                                    "Producto actualizado con exito!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                            else{
                            productViewmodel.insertProduct(
                                ProductEntity(
                                    0,
                                    nameProduct = name,
                                    expireDate = expireDate,
                                    count = count.toInt(),
                                    category = category,
                                    image = imagePath
                                )
                            )
                            navController.navigate(MainScreen.route) {
                                popUpTo(0) { inclusive = true }
                            }

                            Toast.makeText(
                                context,
                                "Producto agregado con exito!",
                                Toast.LENGTH_SHORT
                            ).show()
                            }
                        }

                    }
                },
                colors = ButtonDefaults.buttonColors(
                    contentColor = ColorText,
                    containerColor = ButtonColor
                )
            ) {
                Text(
                    if (product != null) "Actualizar producto." else "Agregar Producto",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 18.sp
                )
            }
        }
    }

    if (showDateDialog) {
        DatePickerDialog(
            onDismissRequest = { showDateDialog = false },
            confirmButton = { Button(onClick = { showDateDialog = false }) { Text("Confirmar") } }
        ) { DatePicker(state) }
    }
}