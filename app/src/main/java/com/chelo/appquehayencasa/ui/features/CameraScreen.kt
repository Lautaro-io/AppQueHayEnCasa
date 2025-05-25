package com.chelo.appquehayencasa.ui.features

import android.Manifest
import android.os.Environment
import android.util.Log
import android.view.ViewGroup
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.NavController
import com.chelo.appquehayencasa.R
import com.chelo.appquehayencasa.ui.features.navigation.MainScreen
import com.chelo.appquehayencasa.ui.theme.ColorText
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import java.io.File
import java.util.concurrent.Executor

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CameraScreen(navController: NavController) {
    val permissions = rememberMultiplePermissionsState(
        listOf(
            Manifest.permission.CAMERA
        )
    )
    val context = LocalContext.current
    val cameraController = remember { LifecycleCameraController(context) }
    val lifecycle = LocalLifecycleOwner.current
    val direction =
        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).absoluteFile

    LaunchedEffect(key1 = Unit) {
        permissions.launchMultiplePermissionRequest()
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(onClick = {
                val executor = ContextCompat.getMainExecutor(context)
                takePicture(cameraController, executor, direction,navController)
            })
            {
                Icon(painterResource(R.drawable.icon_camera), contentDescription = null, tint = ColorText)
            }
        }, floatingActionButtonPosition = FabPosition.Center
    )
    {
        if (permissions.allPermissionsGranted) {
            CameraView(cameraController, lifecycle, modifier = Modifier.padding(it))
        } else {
            Text("Permisos denegados ", modifier = Modifier.padding(it))
        }
    }

}

@Composable
fun CameraView(
    cameraController: LifecycleCameraController,
    lifeCycle: LifecycleOwner,
    modifier: Modifier = Modifier,
) {
    cameraController.bindToLifecycle(lifeCycle)
    AndroidView(
        modifier = modifier,
        factory = {
            val previewView = PreviewView(it).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
            }
            previewView.controller = cameraController
            previewView
        }

    )
}


private fun takePicture(
    cameraController: LifecycleCameraController,
    executor: Executor,
    direction: File,
    navController: NavController
) {
    val image = File.createTempFile("ima_", ".png", direction)
    val output = ImageCapture.OutputFileOptions.Builder(image).build()
    cameraController.takePicture(output, executor, object : ImageCapture.OnImageSavedCallback {
        override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
            navController.navigate(MainScreen.route)
        }

        override fun onError(exception: ImageCaptureException) {
            Log.i("CHELO", exception.toString())

        }
    })
}