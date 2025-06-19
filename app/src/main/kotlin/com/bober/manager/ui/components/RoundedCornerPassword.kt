package com.bober.manager.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bober.manager.R

@Composable
fun RoundedCornerPassword(
    maxLines: Int = 1,
    singleLine: Boolean = true,
    text: String,
    label: String,
    onValueChange: (String) -> Unit,
) {
    val isPasswordVisible = remember { mutableStateOf(false) }

    TextField(
        value = text,
        onValueChange = { onValueChange(it) },
        shape = androidx.compose.foundation.shape.RoundedCornerShape(14.dp), // Изменяем скругление на 12.dp
        colors = TextFieldDefaults.colors(
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp),
        placeholder = {
            Text(text = label, fontSize = 14.sp) // Используем placeholder
        },
        singleLine = singleLine,
        maxLines = maxLines,
        textStyle = LocalTextStyle.current.copy(
            fontSize = 14.sp,
            color = Color.White
        ),
        trailingIcon = {
            if (label == "••••••••") {
                IconButton(
                    onClick = { isPasswordVisible.value = !isPasswordVisible.value }
                ) {
                    Icon(
                        modifier = Modifier.size(20.dp),
                        painter = painterResource(id = if (isPasswordVisible.value) R.drawable.openeye else R.drawable.closeeye),
                        contentDescription = "Показать пароль"
                    )
                }
            }
        },
        visualTransformation = if (isPasswordVisible.value && label == "••••••••") VisualTransformation.None else PasswordVisualTransformation()
    )
}