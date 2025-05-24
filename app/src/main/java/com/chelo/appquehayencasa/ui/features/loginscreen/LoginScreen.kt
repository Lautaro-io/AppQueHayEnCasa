package com.chelo.appquehayencasa.ui.features.loginscreen

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.chelo.appquehayencasa.data.datastore.DataStoreManager
import com.chelo.appquehayencasa.data.entities.UserEntity
import com.chelo.appquehayencasa.ui.features.loginscreen.components.TitleApp
import com.chelo.appquehayencasa.ui.features.loginscreen.components.UserForm
import com.chelo.appquehayencasa.ui.features.navigation.MainScreen
import com.chelo.appquehayencasa.ui.theme.BackgroundColor
import com.chelo.appquehayencasa.viewmodel.UserViewmodel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(navController: NavController, store: DataStoreManager) {
    var name by remember { mutableStateOf("") }
    val context = LocalContext.current
    var viewmodel: UserViewmodel = hiltViewModel()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        TitleApp()
        UserForm(name, onValueChange = {
            name = it
        }) {
            if (name.isNotEmpty()) {
                viewmodel.insertUser(UserEntity(name = name))
                CoroutineScope(Dispatchers.IO).launch {
                    store.saveOnboarding(true)
                }
                Toast.makeText(context, "Usuario agregado con exito! ", Toast.LENGTH_SHORT).show()
                navController.navigate(MainScreen.route) {
                    popUpTo(0) { inclusive = true }
                }

            } else {
                Toast.makeText(context, "Complete todos los campos.", Toast.LENGTH_SHORT).show()

            }

        }


    }
}