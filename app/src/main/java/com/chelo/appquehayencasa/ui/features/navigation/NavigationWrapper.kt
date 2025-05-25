package com.chelo.appquehayencasa.ui.features.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.chelo.appquehayencasa.data.datastore.DataStoreManager
import com.chelo.appquehayencasa.ui.features.CameraScreen
import com.chelo.appquehayencasa.ui.features.loginscreen.LoginScreen
import com.chelo.appquehayencasa.ui.features.mainscreen.MainScreen
import com.chelo.appquehayencasa.ui.features.onboardingscreen.OnboardingScreen
import com.chelo.appquehayencasa.ui.features.splashscreen.SplashScreenApp


@Composable
fun NavigationWrapper(){
    val navController = rememberNavController()
    val context = LocalContext.current
    val dataStore = DataStoreManager(context)
    var store = dataStore.getOnboarding.collectAsState(initial = false)
    NavHost(navController , startDestination = SplashScreenApp.route){
        composable(SplashScreenApp.route) {
            SplashScreenApp(navController , store.value)

        }
        composable (LoginScreen.route) {
            LoginScreen(navController,dataStore)
        }
        composable(OnboardingScreen.route){
            OnboardingScreen(navController)
        }
        composable(MainScreen.route) {
            MainScreen(navController)
        }
        composable(CameraScreen.route){
            CameraScreen(navController)
        }
    }
}