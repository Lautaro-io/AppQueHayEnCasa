package com.chelo.appquehayencasa.ui.features.mainscreen.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.chelo.appquehayencasa.data.entities.ProductEntity
import com.chelo.appquehayencasa.ui.theme.AllCategory
import com.chelo.appquehayencasa.ui.theme.AlmacenCategory
import com.chelo.appquehayencasa.ui.theme.ButtonColor
import com.chelo.appquehayencasa.ui.theme.CleanCategory
import com.chelo.appquehayencasa.ui.theme.ColorText
import com.chelo.appquehayencasa.ui.theme.DefaultCategory
import com.chelo.appquehayencasa.ui.theme.EditBtnColor
import com.chelo.appquehayencasa.ui.theme.ExpireProduct
import com.chelo.appquehayencasa.ui.theme.MeatCategory
import com.chelo.appquehayencasa.ui.theme.Transparent
import java.io.File
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ProductItem(
    product: ProductEntity,
    modifier: Modifier = Modifier,
    onEditButton: () -> Unit,
    onDeleteButton: () -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    val border: Color = when (product.category) {
        "Almacen" -> AlmacenCategory
        "Limpieza" -> CleanCategory
        "Heladera" -> MeatCategory
        "Todos" -> AllCategory
        else -> DefaultCategory
    }
    var scale = if (expanded) ContentScale.None else ContentScale.Crop
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    var bg = Transparent
    var isExpired by remember { mutableStateOf(false) }
    if (product.expireDate != "" && LocalDate.parse(
            product.expireDate,
            formatter
        ) < LocalDate.now()
    ) {
        bg = ExpireProduct
        isExpired = true
    }
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        border = BorderStroke(1.dp, border),
        colors = CardDefaults.cardColors(containerColor = bg),
        onClick = {
            expanded = !expanded
        }
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {
            if (isExpired) RoundedComponent("PRODUCTO VENCIDO" , border)
            Image(
                painter = rememberAsyncImagePainter(File(product.image)),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .size(150.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = scale
            )
            Text(
                product.nameProduct.capitalize(Locale.current),
                color = ColorText,
                modifier = modifier.padding(8.dp),
                fontSize = 24.sp
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = modifier.fillMaxWidth()
            ) {
                RoundedComponent("${product.count} Unidades. ", border = border)
                if (product.expireDate.isNotEmpty())
                    RoundedComponent("Vence: ${product.expireDate}. ", border = border)
            }

            AnimatedVisibility(expanded) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {

                    Button(
                        onClick =
                            onEditButton,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = EditBtnColor,
                            ColorText
                        ),
                    ) {
                        Text("Editar", fontWeight = FontWeight.Bold, color = ColorText)
                    }
                    Button(
                        onClick =
                            onDeleteButton,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = ButtonColor,
                            ColorText
                        )
                    ) {
                        Text("Eliminar", fontWeight = FontWeight.Bold, color = ColorText)
                    }
                }

            }

        }


    }
}


