package com.chelo.appquehayencasa.ui.features.mainscreen.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.chelo.appquehayencasa.data.entities.ProductEntity
import com.chelo.appquehayencasa.ui.theme.AllCategory
import com.chelo.appquehayencasa.ui.theme.AlmacenCategory
import com.chelo.appquehayencasa.ui.theme.BackgroundColor
import com.chelo.appquehayencasa.ui.theme.ButtonColor
import com.chelo.appquehayencasa.ui.theme.CleanCategory
import com.chelo.appquehayencasa.ui.theme.ColorText
import com.chelo.appquehayencasa.ui.theme.DefaultCategory
import com.chelo.appquehayencasa.ui.theme.MeatCategory
import java.io.File


@Composable
fun ProductItem(product: ProductEntity, modifier: Modifier = Modifier, onDeleteButton: () -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    val border: Color = when (product.category) {
        "Almacen" -> AlmacenCategory
        "Limpieza" -> CleanCategory
        "Heladera" -> MeatCategory
        "Todos" -> AllCategory
        else -> DefaultCategory
    }
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        border = BorderStroke(1.dp, border),
        colors = CardDefaults.cardColors(containerColor = BackgroundColor),
        onClick = { expanded = !expanded }
    ) {
        Box(modifier = modifier.fillMaxWidth()) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = modifier.padding(32.dp)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(File(product.image)),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                        .height(100.dp),
                    contentScale = ContentScale.None
                )
                Text(
                    product.nameProduct.capitalize(Locale.current),
                    color = ColorText,
                    modifier = modifier.padding(8.dp)
                )
                Text("Cantidad : ${product.count}", color = ColorText)
                if (product.expireDate.isNotEmpty())
                    Surface(
                        shape = RoundedCornerShape(16.dp),
                        border = BorderStroke(2.dp, border),
                        color = BackgroundColor,
                        modifier = modifier
                            .padding(8.dp).fillMaxWidth()
                    ) {
                        Text(
                            "Vencimiento : ${product.expireDate}",
                            color = ColorText,
                            fontWeight = FontWeight.Bold,
                            modifier = modifier.padding(8.dp)
                        )

                    }
                AnimatedVisibility(expanded) {
                    Button(
                        onClick =
                            onDeleteButton,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = ButtonColor,
                            ColorText
                        ), modifier = modifier.fillMaxWidth()
                    ) {
                        Text("Eliminar producto", fontWeight = FontWeight.Bold, color = ColorText)
                    }
                }

            }


        }
    }


}