package com.chelo.appquehayencasa.ui.features.productform.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.chelo.appquehayencasa.R
import com.chelo.appquehayencasa.ui.features.navigation.CameraScreen
import com.chelo.appquehayencasa.ui.theme.ButtonColor
import com.chelo.appquehayencasa.ui.theme.ColorText
import com.chelo.appquehayencasa.ui.theme.defaulImage
import java.io.File

@Composable
fun ImageContainer(imagePath: String?, navController: NavController ,modifier: Modifier = Modifier ) {
    val image = imagePath?.let { File(it) }
    Box(contentAlignment = Alignment.Center) {
        Row(
            modifier = modifier
                .size(150.dp)
                .clip(CircleShape)
                .background(defaulImage),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (image != null && image.exists()) {
                Image(
                    painter = rememberAsyncImagePainter(image),
                    contentDescription = null,
                    modifier = modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            } else {
                Image(
                    painterResource(R.drawable.empty),
                    contentDescription = null,
                    modifier = Modifier.size(80.dp)
                )
            }


        }
        FloatingActionButton(
            modifier = modifier
                .size(45.dp)
                .padding(0.dp)
                .align(Alignment.BottomEnd),

            onClick = { navController.navigate(CameraScreen.route)},
            containerColor = ButtonColor,
            contentColor = ColorText,
            shape = CircleShape,

            ) { Icon(Icons.Default.Add, contentDescription = null) }
    }
}
