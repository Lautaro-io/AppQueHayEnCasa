package com.chelo.appquehayencasa.ui.features.splashscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.chelo.appquehayencasa.R
import com.chelo.appquehayencasa.ui.features.navigation.LoginScreen
import com.chelo.appquehayencasa.ui.features.navigation.OnboardingScreen
import com.chelo.appquehayencasa.ui.theme.BackgroundColor
import com.chelo.appquehayencasa.ui.theme.ColorText
import kotlinx.coroutines.delay


@Composable
fun SplashScreenApp(navController : NavController) {
    LaunchedEffect(true) {
        delay(1000)
        navController.navigate(OnboardingScreen.route) {
            popUpTo(0) { inclusive = true }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painterResource(R.drawable.appicon),
            contentDescription = "App Icon",
            modifier = Modifier.size(128.dp)
        )
        Text(
            text = "Que hay en casa?",
            color = ColorText,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )
    }

}

