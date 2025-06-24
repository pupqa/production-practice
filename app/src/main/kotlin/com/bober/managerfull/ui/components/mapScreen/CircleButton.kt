package com.bober.managerfull.ui.components.mapScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bober.managerfull.ui.theme.Yellow

@Composable
fun CircleButton(
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        modifier = modifier
            .size(24.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Yellow
        ),
        shape = (CircleShape),
        contentPadding = PaddingValues(0.dp)
    ) {
        Text(
            text = title,
            color = Color.Black,
            fontSize = 16.sp,
            style = MaterialTheme.typography.bodyMedium
        )

    }
}

@Preview(showBackground = true)
@Composable
fun CircleButtonPreview() {
    MaterialTheme {
        CircleButton(
            title = "+",
            onClick = { }
        )
    }
}