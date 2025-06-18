package com.chelo.appquehayencasa.ui.features.mainscreen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.chelo.appquehayencasa.data.entities.CategoryEntity
import com.chelo.appquehayencasa.data.entities.ProductEntity
import com.chelo.appquehayencasa.ui.features.mainscreen.components.CategoryDialog
import com.chelo.appquehayencasa.ui.features.mainscreen.components.DialogDeleteDialog
import com.chelo.appquehayencasa.ui.features.mainscreen.components.DialogDeleteProduct
import com.chelo.appquehayencasa.ui.features.mainscreen.components.EmptyProduct
import com.chelo.appquehayencasa.ui.features.mainscreen.components.ProductCategory
import com.chelo.appquehayencasa.ui.features.mainscreen.components.ProductItem
import com.chelo.appquehayencasa.ui.features.mainscreen.components.TitleSection
import com.chelo.appquehayencasa.ui.features.models.Category
import com.chelo.appquehayencasa.ui.features.navigation.ProductForm
import com.chelo.appquehayencasa.ui.theme.AllCategory
import com.chelo.appquehayencasa.ui.theme.BackgroundColor
import com.chelo.appquehayencasa.ui.theme.ButtonColor
import com.chelo.appquehayencasa.ui.theme.ColorObject
import com.chelo.appquehayencasa.ui.theme.ColorObject.Companion.basicColors
import com.chelo.appquehayencasa.ui.theme.ColorText
import com.chelo.appquehayencasa.viewmodel.CategoryViewmodel
import com.chelo.appquehayencasa.viewmodel.ProductViewmodel


@Composable
fun MainScreen(navController: NavController) {
    val context = LocalContext.current

    val productViewmodel: ProductViewmodel = hiltViewModel()
    val categoryViewmodel: CategoryViewmodel = hiltViewModel()
    var showDialogDelete by remember { mutableStateOf(false) }
    var showDialogDeleteCategory by remember { mutableStateOf(false) }
    val categoriesEntities by categoryViewmodel.allCategories.collectAsState(emptyList())
    var colorReceiver: ColorObject = basicColors[0]

    val categoryList = categoriesEntities.map {
        Category(
            it.name,
            color = if (it.name.lowercase() == "todos") AllCategory else colorReceiver.color
        )
    }
    var showCategoryDialog by remember { mutableStateOf(false) }

    var selectedCategory by remember { mutableStateOf("Todos") }


    var filterState by remember { mutableStateOf(false) }

    val products by productViewmodel.allProducts.collectAsState()
    val filteredProducts by productViewmodel.filteredProducts.collectAsState()
    val productsToShow = if (filterState) filteredProducts else products

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                FloatingActionButton(
                    onClick = { navController.navigate(ProductForm.route) },
                    modifier = Modifier.padding(30.dp),
                    containerColor = ButtonColor,
                    contentColor = ColorText
                ) {
                    Icon(
                        Icons.Default.Add,
                        contentDescription = ""
                    )
                }
            }
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .background(BackgroundColor)
        ) {
            TitleSection(
                "Productos en la casa"
            )
            ProductCategory(
                categoryList,
                selectedCategory = selectedCategory,
                onItemSelected = { category ->
                    selectedCategory = category
                    if (category != "Todos") {
                        productViewmodel.filterProductsByCategory(category)
                        filterState =
                            true
                    } else {
                        filterState = false
                    }
                }, onAddClickButton = { showCategoryDialog = true },
                onLongPressed = {
                    if (it == "Todos") {
                        Toast.makeText(
                            context,
                            "No se puede eliminar esta categoria",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        showDialogDeleteCategory = true
                        selectedCategory = it

                    }
                })



            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                if (productsToShow.isEmpty()) {
                    EmptyProduct()
                } else {
                    LazyColumn(modifier = Modifier.padding(horizontal = 20.dp)) {
                        itemsIndexed(productsToShow.reversed()) { index, item ->
                            ProductItem(
                                item,
                                onEditButton = {
                                    navController.navigate("${ProductForm.route}?imagePath=${item.image}&productId=${item.id}")
                                },
                                onDeleteButton = { showDialogDelete = true })

                            when {
                                showDialogDelete ->
                                    DialogDeleteProduct(onDismissClick = {
                                        showDialogDelete = false
                                    }) {
                                        productViewmodel.deleteProduct(item)
                                        showDialogDelete = false
                                    }
                            }
                        }
                    }
                }


                when {
                    showCategoryDialog ->
                        CategoryDialog(
                            onDismissClick = { showCategoryDialog = false },
                            onConfirmButton = { name, color ->
                                if (!categoryViewmodel.isCategorySaved(name)) {
                                    categoryViewmodel.insertCategory(CategoryEntity(name = name))
                                    colorReceiver = color
                                    showCategoryDialog = false
                                } else
                                    Toast.makeText(
                                        context,
                                        "No pueden haber 2 categorias con el mismo nombre!",
                                        Toast.LENGTH_SHORT
                                    ).show()

                            }
                        )

                    showDialogDeleteCategory ->
                        DialogDeleteDialog(
                            onConfirmButton = {
                                productViewmodel.deleteProductsByCategory(selectedCategory)
                                categoryViewmodel.deleteCategoryByName(selectedCategory)
                                showDialogDeleteCategory = false
                            },
                            onDismissButton = { showDialogDeleteCategory = false }
                        )


                }

            }


        }

    }
}