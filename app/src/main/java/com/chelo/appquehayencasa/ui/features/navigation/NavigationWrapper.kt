package com.chelo.appquehayencasa.ui.features.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.chelo.appquehayencasa.data.datastore.DataStoreManager
import com.chelo.appquehayencasa.ui.features.CameraScreen
import com.chelo.appquehayencasa.ui.features.loginscreen.LoginScreen
import com.chelo.appquehayencasa.ui.features.mainscreen.MainScreen
import com.chelo.appquehayencasa.ui.features.onboardingscreen.OnboardingScreen
import com.chelo.appquehayencasa.ui.features.productform.ProductForm
import com.chelo.appquehayencasa.ui.features.splashscreen.SplashScreenApp


@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val dataStore = DataStoreManager(context)
    var store = dataStore.getOnboarding.collectAsState(initial = false)
    NavHost(navController, startDestination = SplashScreenApp.route) {
        composable(SplashScreenApp.route) {
            SplashScreenApp(navController, store.value)

        }
        composable(LoginScreen.route) {
            LoginScreen(navController, dataStore)
        }
        composable(OnboardingScreen.route) {
            OnboardingScreen(navController)
        }
        composable(MainScreen.route) {
            MainScreen(navController)
        }
        composable(CameraScreen.route) {
            CameraScreen(navController)
        }
        composable(
            route = "${ProductForm.route}?imagePath={imagePath}",
            arguments = listOf(
                navArgument("imagePath"){
                    type = NavType.StringType
                    nullable= true
                    defaultValue= null
                }
            )
        ) { backStackEntry ->
            val imagePath = backStackEntry.arguments?.getString("imagePath")
            val decodeImage = Uri.decode(imagePath ?: "")
            ProductForm(decodeImage, navController)
        }
    }

}