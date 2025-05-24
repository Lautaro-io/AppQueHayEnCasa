package com.chelo.appquehayencasa.ui.features.mainscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.chelo.appquehayencasa.R
import com.chelo.appquehayencasa.ui.features.mainscreen.components.TitleSection
import com.chelo.appquehayencasa.ui.theme.BackgroundColor
import com.chelo.appquehayencasa.ui.theme.ButtonColor
import com.chelo.appquehayencasa.ui.theme.ColorText


@Preview
@Composable
fun MainScreen() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                FloatingActionButton(
                    onClick = { print("") },
                    modifier = Modifier
                        .padding(horizontal = 15.dp)
                        .size(40.dp),
                    containerColor = ButtonColor,
                    contentColor = ColorText
                ) {
                    Image(painterResource(R.drawable.cocinero), contentDescription = "")
                }
                FloatingActionButton(
                    onClick = { print("") },
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
            TitleSection("Productos en la casa", modifier = Modifier.padding(vertical = 30.dp , horizontal = 8.dp))

        }
    }
}