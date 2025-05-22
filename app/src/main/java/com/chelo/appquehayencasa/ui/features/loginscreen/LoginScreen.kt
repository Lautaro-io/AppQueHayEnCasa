package com.chelo.appquehayencasa.ui.features.loginscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.chelo.appquehayencasa.ui.features.loginscreen.components.TitleApp
import com.chelo.appquehayencasa.ui.features.loginscreen.components.UserForm
import com.chelo.appquehayencasa.ui.theme.BackgroundColor

@Composable
fun LoginScreen(){
    var name by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        TitleApp()
        UserForm(name) {
            name = it
        }


    }
}