package com.bober.managerfull.ui.components.loginScreen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bober.managerfull.ui.theme.GrayExtraDark
import com.bober.managerfull.ui.theme.Hint
import com.bober.managerfull.ui.theme.White

@Composable
fun RoundedCorner(
    maxLines: Int = 1,
    singleLine: Boolean = true,
    text: String,
    label: String,
    onValueChange: (String) -> Unit,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    focusRequester: FocusRequester = remember { FocusRequester() },
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .border(
                width = 0.8.dp,
                color = Hint.copy(0.4f),
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        TextField(
            value = text,
            onValueChange = { newValue ->
                val noSpacesValue = newValue.replace(" ", "")
                onValueChange(noSpacesValue)
            },
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = GrayExtraDark,
                focusedContainerColor = GrayExtraDark.copy(alpha = 0.9f),
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedTextColor = Hint.copy(alpha = 0.8f),
                focusedTextColor = White,
                unfocusedPlaceholderColor = White.copy(alpha = 0.6f),
                focusedPlaceholderColor = White.copy(alpha = 0.6f),
                cursorColor = White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .focusRequester(focusRequester),
            placeholder = {
                Text(text = label, fontSize = 14.sp, style = MaterialTheme.typography.bodyMedium)
            },
            singleLine = singleLine,
            maxLines = maxLines,
            textStyle = LocalTextStyle.current.copy(
                fontSize = 14.sp
            ),
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions
        )
    }
}