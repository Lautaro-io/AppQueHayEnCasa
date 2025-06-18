package com.chelo.appquehayencasa.ui.features.navigation

import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
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


@RequiresApi(Build.VERSION_CODES.O)
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
            route = "${ProductForm.route}?imagePath={imagePath}&productId={productId}",

            arguments = listOf(
                navArgument("imagePath"){
                    type = NavType.StringType
                    nullable= true
                    defaultValue= null
                },
                navArgument("productId") {
                    type = NavType.LongType
                    defaultValue = -1L
                }
            )
        ) { backStackEntry ->
            val imagePath = backStackEntry.arguments?.getString("imagePath")
            val decodeImage = Uri.decode(imagePath ?: "")
            val productId = backStackEntry.arguments?.getLong("productId").takeIf { it != -1L }
            ProductForm(decodeImage, navController , productId)
        }
    }

}