package com.chelo.appquehayencasa.ui.features.mainscreen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.chelo.appquehayencasa.data.entities.ProductEntity
import com.chelo.appquehayencasa.ui.features.models.categories
import com.chelo.appquehayencasa.ui.theme.AllCategory
import com.chelo.appquehayencasa.ui.theme.AlmacenCategory
import com.chelo.appquehayencasa.ui.theme.CleanCategory
import com.chelo.appquehayencasa.ui.theme.ColorText
import com.chelo.appquehayencasa.ui.theme.MeatCategory
import java.io.File

@Composable
fun ProductItem(product: ProductEntity, modifier: Modifier = Modifier) {

    val border : Color = when(product.category){
        "Almacen" -> AlmacenCategory
        "Limpieza" -> CleanCategory
        "Carne" -> MeatCategory
        else -> AllCategory
    }
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        border = BorderStroke(1.dp , border)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = modifier.padding(16.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(File(product.image)),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .height(100.dp),
                contentScale = ContentScale.None
            )
            Text(product.nameProduct.capitalize(Locale.current), color = ColorText)
            Text("Cantidad : ${product.count}", color = ColorText)

        }

    }

}