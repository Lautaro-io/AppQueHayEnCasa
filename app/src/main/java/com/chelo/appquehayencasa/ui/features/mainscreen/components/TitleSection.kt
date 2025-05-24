package com.chelo.appquehayencasa.ui.features.mainscreen.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.chelo.appquehayencasa.ui.theme.SubcolorText


@Composable
fun TitleSection(text: String, modifier: Modifier = Modifier){
    Row (verticalAlignment = Alignment.CenterVertically, modifier = modifier.fillMaxWidth()){
        Text(text, fontWeight = FontWeight.ExtraBold, color = SubcolorText)
        Divider(color = SubcolorText, thickness = 1.dp,modifier = modifier.weight(1f))

    }
}