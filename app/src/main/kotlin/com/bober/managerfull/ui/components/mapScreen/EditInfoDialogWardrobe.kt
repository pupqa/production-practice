package com.bober.managerfull.ui.components.mapScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun EditInfoDialogWardrobe(
    title: String,
    description: String,
    initialAdditionalFields: List<String>, // Добавлен параметр для начальных значений доп. полей
    onDismiss: () -> Unit,
    onTitle: (String) -> Unit,
    onDescription: (String) -> Unit,
    onSave: (String, String, List<String>) -> Unit,
) {
    val name = remember { mutableStateOf(title) }
    val content = remember { mutableStateOf(description) }
    val additionalFields = remember { mutableStateListOf<String>().apply { addAll(initialAdditionalFields) } }
    var showEmptyFieldWarning by remember { mutableStateOf(false) }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties()
    ) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color.White,
            modifier = Modifier
                .widthIn(min = 280.dp, max = 560.dp)
                .heightIn(max = 560.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(bottom = 40.dp)
                    .background(Color.White)
            ) {
                Text(
                    text = "Редактирование информации",
                    color = Color.Black,
                    lineHeight = 40.sp,
                    fontSize = 32.sp,
                    modifier = Modifier.padding(10.dp),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyMedium
                )

                if (showEmptyFieldWarning) {
                    Text(
                        text = "Заполните все обязательные поля!",
                        color = Color.Red,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }

                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    item {
                        TextFields(
                            value = name.value,
                            text = "Инвентарный номер*",
                            onvalChange = {
                                name.value = it
                                onTitle(it)
                            }
                        )
                    }

                    item {
                        TextFields(
                            value = content.value,
                            text = "Содержимое*",
                            onvalChange = {
                                content.value = it
                                onDescription(it)
                            }
                        )
                    }

                    items(additionalFields.size) { index ->
                        TextFields(
                            value = additionalFields[index],
                            text = "Дополнительное поле ${index + 1}",
                            onvalChange = { newValue ->
                                additionalFields[index] = newValue
                            }
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                CircleButton(
                    title = "+",
                    onClick = {
                        additionalFields.add("")
                    },
                    modifier = Modifier
                        .size(30.dp)
                        .offset(y = (-26).dp)
                )

                Spacer(modifier = Modifier.height(15.dp))

                CustomButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    title = "Сохранить",
                    onClick = {
                        if (name.value.isBlank() || content.value.isBlank()) {
                            showEmptyFieldWarning = true
                        } else {
                            onSave(
                                name.value,
                                content.value,
                                additionalFields.filter { it.isNotBlank() }
                            )
                            onDismiss()
                        }
                    }
                )
            }
        }
    }
}