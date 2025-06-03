package com.chelo.appquehayencasa.ui.features.models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import com.chelo.appquehayencasa.ui.theme.ColorText


data class Category(
 val name: String,
 val color: Color = ColorText,
 var isSelected: MutableState<Boolean> = mutableStateOf(false),
)


