package com.chelo.appquehayencasa.ui.theme

import androidx.compose.ui.graphics.Color

sealed class ColorObject(val name: String, val color: Color) {

    object OceanBlue : ColorObject("Ocean Blue", Color(0xFF0077BE))
    object ConfirmRed : ColorObject("Confirm Red", Color(0xFFFF2A00))
    object GrassGreen : ColorObject("Grass Green", Color(0xFF00C853))
    object SunYellow : ColorObject("Sun Yellow", Color(0xFFFFEB3B))
    object SkyBlue : ColorObject("Sky Blue", Color(0xFF03A9F4))
    object DeepPurple : ColorObject("DeepPurple", Color(0xFF673AB7))
    object LightGrey : ColorObject("LightGrey", Color(0xFFBDBDBD))
    object DarkGrey : ColorObject("DarkGrey", Color(0xFF424242))
    object BrightOrange : ColorObject("BrightOrange", Color(0xFFFF9800))
    object SoftPink : ColorObject("SoftPink", Color(0xFFF8BBD0))
    object CleanWhite : ColorObject("CleanWhite", Color(0xFFFFFFFF))
    object JetBlack : ColorObject("JetBlack", Color(0xFF000000))


    companion object {
        val basicColors = listOf<ColorObject>(
            OceanBlue,
            ConfirmRed,
            GrassGreen,
            SunYellow,
            SkyBlue,
            DeepPurple,
            LightGrey,
            DarkGrey,
            BrightOrange,
            SoftPink,
            CleanWhite,
            JetBlack
        )
    }
}