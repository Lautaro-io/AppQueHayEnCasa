package com.chelo.appquehayencasa.ui.features.mainscreen.components

import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.chelo.appquehayencasa.data.entities.ProductEntity

@Composable
fun ProductItem(product: ProductEntity, borderColor: Color) {
    Card(
        colors = CardDefaults.cardColors(
            borderColor
        )
    ) {

    }

}