package com.chelo.appquehayencasa.ui.features.mainscreen.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.chelo.appquehayencasa.ui.theme.SubcolorText


@Composable
fun TitleSection(text: String, modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 30.dp , start = 25.dp)
    ) {
        Text(
            text,
            fontWeight = FontWeight.ExtraBold,
            color = SubcolorText,
            modifier = modifier.padding(horizontal = 4.dp)
        )
        HorizontalDivider(color = SubcolorText, thickness = 1.dp, modifier = modifier.weight(1f))

    }
}