package com.bober.managerfull.ui.components.mapScreen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bober.managerfull.model.Workstation

@Composable
fun WorkstationPoint(
    workstation: Workstation,
    onClick: () -> Unit,
    onLongClick: () -> Unit,
    modifier: Modifier = Modifier,
) {

    Log.d(
        "WorkstationPoint",
        "Workstation ID: ${workstation.id}, " +
                "Employee: ${workstation.employeeName}, " +
                "Position: ${workstation.position}"
    )

    val pointColor = if (workstation.employeeName.isNotEmpty()) {
        Log.d("WorkstationPoint", "Setting color to GREEN (occupied) for ${workstation.id}")
        Color.Red.copy(alpha = 0.7f)
    } else {
        Log.d("WorkstationPoint", "Setting color to RED (free) for ${workstation.id}")
        Color.Green.copy(alpha = 0.7f)
    }

    Box(
        modifier = modifier
            .size(28.dp)
            .shadow(
                elevation = 4.dp,
                shape = CircleShape,
                ambientColor = Color.Black.copy(alpha = 0.3f),
                spotColor = Color.Black.copy(alpha = 0.3f)
            )
            .clip(CircleShape)
            .background(
                color = Color.Gray.copy(alpha = 0.2f),
                shape = CircleShape
            )
            .border(
                width = 1.5.dp,
                color = Color.White.copy(alpha = 0.8f),
                shape = CircleShape
            )
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = { onClick() },
                    onLongPress = { onLongClick() }
                )
            },
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(20.dp)
                .clip(CircleShape)
                .background(pointColor)
                .border(
                    width = 1.dp,
                    color = Color.White.copy(alpha = 0.6f),
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = workstation.id,
                color = Color.White,
                fontSize = 10.sp,
                style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.Bold,
                    shadow = Shadow(
                        color = Color.Black.copy(alpha = 0.5f),
                        offset = Offset(1f, 1f),
                        blurRadius = 2f
                    )
                )
            )
        }
    }
}