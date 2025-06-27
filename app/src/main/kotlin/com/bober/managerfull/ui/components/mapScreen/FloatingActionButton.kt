package com.bober.managerfull.ui.components.mapScreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
            if (enabled) onClick()
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = color,
//            contentColor = Color.Black,
//            disabledContainerColor = color.copy(alpha = 0.5f)
        ),
        enabled = enabled,
        modifier = Modifier
            .padding(bottom = 8.dp)
            .size(56.dp),
        shape = RoundedCornerShape(15.dp),
        border = BorderStroke(1.dp, Color.Gray) // Border как часть кнопки
    ) {
        Text(
            text = text,
            color = Color.Black,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}