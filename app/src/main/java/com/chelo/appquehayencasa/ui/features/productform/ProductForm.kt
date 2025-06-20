package com.chelo.appquehayencasa.ui.features.productform

import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
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
fun ProductForm(imagePath: String, navController: NavController, productId: Long?) {
    val context = LocalContext.current

    val vm: ProductViewmodel = hiltViewModel()
    val categoryViewmodel: CategoryViewmodel = hiltViewModel()
    var expanded by remember { mutableStateOf(false) }
    val categories by categoryViewmodel.allCategories.collectAsState(emptyList())

    LaunchedEffect(productId) {
        productId?.let {
            vm.loadProduct(it)
        }
    }
    val state = rememberDatePickerState()
    var showDateDialog by remember { mutableStateOf(false) }
    val date = state.selectedDateMillis
    date?.let {
        val localDate = Instant.ofEpochMilli(it).atZone(ZoneId.of("UTC")).toLocalDate()
        val internalFormat = localDate.format(DateTimeFormatter.ISO_LOCAL_DATE)
        vm.onExpireChanged(internalFormat)
    }

    Scaffold(topBar = {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(BackgroundColor),
            title = { TitleSection("Agregar producto") },
            navigationIcon = {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Volver",
                        tint = SubcolorText
                    )
                }
            }
        )
    }) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundColor)
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            ImageContainer(imagePath, navController)
            Spacer(modifier = Modifier.height(80.dp))

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                OutlinedTextField(
                    value = vm.name,
                    onValueChange = { vm.onNameChanged(it) },
                    placeholder = { Text("Nombre del producto") },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = ColorText,
                        unfocusedContainerColor = ColorText,
                        focusedTextColor = BlackText,
                        unfocusedTextColor = BlackText
                    ),
                    leadingIcon = { Icon(Icons.Default.PlayArrow, contentDescription = "") },
                    shape = RoundedCornerShape(32.dp), singleLine = true
                )

                OutlinedTextField(
                    value = vm.count,
                    onValueChange = vm::onCountChanged,
                    placeholder = { Text("Cantidad") },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = ColorText,
                        unfocusedContainerColor = ColorText,
                        focusedTextColor = BlackText,
                        unfocusedTextColor = BlackText
                    ),
                    leadingIcon = { Icon(Icons.Default.Info, contentDescription = "") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    shape = RoundedCornerShape(32.dp), singleLine = true
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
                        value = if (vm.category != "") vm.category else "Categoria",
                        onValueChange = { },
                        readOnly = true,
                        placeholder = { Text("Categoria", color = SubcolorText) },
                        leadingIcon = { Icon(Icons.Default.Star, contentDescription = "str") },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = ColorText,
                            unfocusedContainerColor = ColorText,
                            focusedTextColor = BlackText,
                            unfocusedTextColor = SubcolorText
                        ),
                        shape = RoundedCornerShape(32.dp), singleLine = true
                    )
                    ExposedDropdownMenu(expanded, onDismissRequest = { expanded = false }) {
                        categories.forEach { categoryItem ->
                            DropdownMenuItem(
                                text = { Text(categoryItem.name) },
                                onClick = {
                                    vm.onCategoryChanged(categoryItem.name)
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
                        .padding(horizontal = 64.dp)

                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(1.dp),
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.DateRange, contentDescription = "date")
                        Text(
                            if (vm.expireDate != "") "Vencimiento : ${vm.expireDate}" else "Fecha de vencimiento(opcional)",
                            fontWeight = FontWeight.Light,
                            color = BlackText
                        )
                    }


                }

            }
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 64.dp, vertical = 24.dp),
                onClick =
                    {
                        if (listOf(vm.name, vm.count, vm.category).any { it.isEmpty() }) {
                            Toast.makeText(context, "Complete todos los campos", Toast.LENGTH_SHORT)
                                .show()
                        } else {
                            vm.saveProduct(imagePath, productId)
                            navController.navigate(MainScreen.route) {
                                popUpTo(0) {
                                    inclusive = true
                                }
                            }
                            if (productId != null) showToast(
                                "Producto actualizado con éxito!",
                                context
                            ) else showToast("Producto agregado con éxito!", context)

                        }

                    },
                colors = ButtonDefaults.buttonColors(
                    contentColor = ColorText,
                    containerColor = ButtonColor
                )
            ) {
                Text(
                    if (productId != null) "Actualizar producto." else "Agregar Producto",
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


private fun showToast(msg: String, context: Context) {
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
}