package com.chelo.appquehayencasa.ui.features.mainscreen.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.chelo.appquehayencasa.ui.theme.BackgroundColor
import com.chelo.appquehayencasa.ui.theme.ColorText

@Composable
fun RoundedComponent(
    text: String,
    border: Color,
    modifier: Modifier = Modifier,
) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(2.dp, border),
        color = BackgroundColor,
        modifier = modifier
            .padding(8.dp)
    ) {
        Text(
            text = text,
            color = ColorText,
            fontWeight = FontWeight.Bold,
            modifier = modifier.padding(8.dp)
        )

    }
}