package com.bober.managerfull.ui.components.mapScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.TurnedIn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.bober.managerfull.model.Wardrobe

@Composable
fun WardrobeInfoDialog(
    wardrobe: Wardrobe,
    onDismiss: () -> Unit,
    onEditInventory: (String) -> Unit,
) {

    var isEditing by remember { mutableStateOf(false) }
    var tempInventoryNumber by remember { mutableStateOf(wardrobe.wardrobeName) }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties()
    ) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = MaterialTheme.colorScheme.surface,
            shadowElevation = 8.dp,
            modifier = Modifier
                .widthIn(min = 280.dp, max = 560.dp)
                .heightIn(max = 560.dp)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Шкаф ${wardrobe.wardrobeName}",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 8.dp),
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.outlineVariant
                )

                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    item {
                        if (isEditing) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                OutlinedTextField(
                                    value = tempInventoryNumber,
                                    onValueChange = { tempInventoryNumber = it },
                                    label = { Text("Инвентарный номер") },
                                    modifier = Modifier.weight(1f)
                                )

                                IconButton(
                                    onClick = {
                                        onEditInventory(tempInventoryNumber)
                                        isEditing = false
                                    }
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Check,
                                        contentDescription = "Сохранить",
                                        tint = Color.Green
                                    )
                                }

                                IconButton(
                                    onClick = {
                                        isEditing = false
                                        tempInventoryNumber = wardrobe.wardrobeName
                                    }
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Close,
                                        contentDescription = "Отмена",
                                        tint = Color.Red
                                    )
                                }
                            }
                        } else {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Info,
                                        contentDescription = "Инвентарный номер",
                                        tint = Color.DarkGray,
                                        modifier = Modifier.size(20.dp)
                                    )
                                    Spacer(modifier = Modifier.width(12.dp))
                                    Column {
                                        Text(
                                            text = "Инвентарный номер",
                                            style = MaterialTheme.typography.labelMedium,
                                            color = Color.DarkGray
                                        )
                                        Text(
                                            text = wardrobe.wardrobeName,
                                            style = MaterialTheme.typography.bodyLarge,
                                            color = Color.Black
                                        )
                                    }
                                }

                                IconButton(
                                    onClick = { isEditing = true },
                                    modifier = Modifier.padding(start = 8.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Edit,
                                        contentDescription = "Редактировать",
                                        tint = Color.Black
                                    )
                                }
                            }
                        }
                    }

                    item {
                        InfoRow(
                            icon = Icons.AutoMirrored.Filled.List,
                            title = "Содержимое",
                            value = wardrobe.content
                        )
                    }

                    wardrobe.additionalFields?.takeIf { it.isNotEmpty() }
                        ?.forEachIndexed { index, field ->
                            item {
                                InfoRow(
                                    icon = Icons.Default.TurnedIn,
                                    title = "Доп. содержимое ${index + 1}",
                                    value = field
                                )
                            }
                        }
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}