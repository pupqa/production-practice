package com.bober.managerfull.ui.components.loginScreen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bober.managerfull.R
import com.bober.managerfull.ui.theme.GrayExtraDark
import com.bober.managerfull.ui.theme.Hint
import com.bober.managerfull.ui.theme.White

@Composable
fun RoundedCornerPassword(
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

    val isPasswordVisible = remember { mutableStateOf(false) }

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
                Text(
                    text = label,
                    fontSize = 14.sp,
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            singleLine = singleLine,
            maxLines = maxLines,
            textStyle = LocalTextStyle.current.copy(
                fontSize = 14.sp
            ),
            trailingIcon = {
                if (label == stringResource(R.string.hint_password_masked)) {
                    IconButton(
                        onClick = { isPasswordVisible.value = !isPasswordVisible.value }
                    ) {
                        Icon(
                            modifier = Modifier.size(20.dp),
                            painter = painterResource(
                                id = if (isPasswordVisible.value) R.drawable.open_eye
                                else R.drawable.close_eye
                            ),
                            contentDescription = "Показать пароль",
                            tint = Hint.copy(alpha = 0.8f)
                        )
                    }
                }
            },
            visualTransformation = if (
                isPasswordVisible.value && label == stringResource(R.string.hint_password_masked)) VisualTransformation.None
            else PasswordVisualTransformation(),
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions
        )
    }
}