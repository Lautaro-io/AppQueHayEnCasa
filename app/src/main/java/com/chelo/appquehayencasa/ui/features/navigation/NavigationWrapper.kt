package com.chelo.appquehayencasa.ui.features.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.chelo.appquehayencasa.ui.features.loginscreen.LoginScreen
import com.chelo.appquehayencasa.ui.features.mainscreen.MainScreen
import com.chelo.appquehayencasa.ui.features.onboardingscreen.OnboardingScreen
import com.chelo.appquehayencasa.ui.features.splashscreen.SplashScreenApp


@Composable
fun NavigationWrapper(){
    val navController = rememberNavController()

    NavHost(navController , startDestination = SplashScreenApp.route){
        composable(SplashScreenApp.route) {
            SplashScreenApp(navController)

        }
        composable (LoginScreen.route) {
            LoginScreen(navController)
        }
        composable(OnboardingScreen.route){
            OnboardingScreen(navController)
        }
        composable(MainScreen.route) {
            MainScreen()
        }
    }
}