package com.chelo.appquehayencasa.ui.features.loginscreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chelo.appquehayencasa.R
import com.chelo.appquehayencasa.ui.theme.BackgroundColor
import com.chelo.appquehayencasa.ui.theme.ColorText

@Composable
fun TitleApp(modifier : Modifier = Modifier ){
    Column(
        modifier = modifier
            .background(BackgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painterResource(R.drawable.appicon),
            contentDescription = "App Icon",
            modifier = Modifier.size(110.dp)
        )
        Spacer(modifier.height(16.dp))
        Text(
            text = "Que hay en casa?",
            color = ColorText,
            fontSize = 30.sp,
            fontWeight = FontWeight.ExtraBold
        )
    }
}