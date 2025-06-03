package com.chelo.appquehayencasa.ui.features.mainscreen

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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.chelo.appquehayencasa.data.entities.CategoryEntity
import com.chelo.appquehayencasa.ui.features.mainscreen.components.CategoryDialog
import com.chelo.appquehayencasa.ui.features.mainscreen.components.DialogDeleteProduct
import com.chelo.appquehayencasa.ui.features.mainscreen.components.EmptyProduct
import com.chelo.appquehayencasa.ui.features.mainscreen.components.ProductCategory
import com.chelo.appquehayencasa.ui.features.mainscreen.components.ProductItem
import com.chelo.appquehayencasa.ui.features.mainscreen.components.TitleSection
import com.chelo.appquehayencasa.ui.features.models.Category
import com.chelo.appquehayencasa.ui.features.navigation.ProductForm
import com.chelo.appquehayencasa.ui.theme.BackgroundColor
import com.chelo.appquehayencasa.ui.theme.ButtonColor
import com.chelo.appquehayencasa.ui.theme.ColorText
import com.chelo.appquehayencasa.viewmodel.CategoryViewmodel
import com.chelo.appquehayencasa.viewmodel.ProductViewmodel


@Composable
fun MainScreen(navController: NavController) {
    val productViewmodel: ProductViewmodel = hiltViewModel()
    val categoryViewmodel: CategoryViewmodel = hiltViewModel()
    var showDialogDelete by remember { mutableStateOf(false) }

    val categoriesEntities by categoryViewmodel.allCategories.collectAsState(emptyList())
    val categoryList = categoriesEntities.map {
        Category(it.name,
            isSelected = mutableStateOf(it.name.lowercase() == "todos"))
    }

    var showCategoryDialog by remember { mutableStateOf(false) }


    var filterState by remember { mutableStateOf(false) }

    var products = productViewmodel.allProducts.collectAsState(emptyList())
    var filteredProducts = productViewmodel.filteredProducts.collectAsState()
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
                categoryList
                ,
                onItemSelected = { category ->
                    if (category != "Todos") {
                        productViewmodel.filterProductsByCategory(category)
                        filterState =
                            true // Bug que cuando apretas de nuevo otra categoria , mecanicamente el state es false por ende muestra la lista principal
                    } else {
                        filterState = false
                    }
                }, onAddClickButton = { showCategoryDialog = true })



            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                if (productsToShow.value.isEmpty()) {
                    EmptyProduct()
                } else {
                    LazyColumn(modifier = Modifier.padding(horizontal = 20.dp)) {
                        itemsIndexed(productsToShow.value.reversed()) { index, item ->
                            ProductItem(
                                item,
                                onDeleteButton = { showDialogDelete = true })
                            if (showDialogDelete)
                                DialogDeleteProduct(onDismissClick = { showDialogDelete = false }) {
                                    productViewmodel.deleteProduct(item)
                                    showDialogDelete = false
                                }
                        }
                    }
                }
                if (showCategoryDialog) {
                    CategoryDialog(
                        onDismissClick = { showCategoryDialog = false },
                        onConfirmButton = {
                            categoryViewmodel.insertCategory(CategoryEntity(name = it))
                            showCategoryDialog = false
                        }
                    )
                }
            }

        }


    }


}