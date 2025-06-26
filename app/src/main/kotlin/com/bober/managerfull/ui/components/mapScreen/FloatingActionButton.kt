package com.bober.managerfull.ui.components.mapScreen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun FloatingActionButtonFun(
    text: String,
    color: Color,
    onClick: () -> Unit,
    enabled: Boolean
) {
    Button(
        onClick = {
            onClick()
        },
        colors = ButtonDefaults.buttonColors(containerColor = color),
        enabled = enabled,
        modifier = Modifier
            .padding(bottom = 8.dp)
            .border(
                width = 1.dp,
                color = Color.Gray,
                shape = RoundedCornerShape(15.dp)
            )
    ) {
        Text(text = text, color = Color.Black, style = MaterialTheme.typography.bodyMedium)
    }
}