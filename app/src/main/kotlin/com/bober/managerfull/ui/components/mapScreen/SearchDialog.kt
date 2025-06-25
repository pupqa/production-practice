package com.bober.managerfull.ui.components.mapScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewModelScope
import com.bober.managerfull.model.OperationState
import com.bober.managerfull.model.Wardrobe
import com.bober.managerfull.model.Workstation
import com.bober.managerfull.ui.screens.officeMap.OfficeMapScreenViewModel
import kotlinx.coroutines.launch

@Composable
fun SearchDialog(
    viewModel: OfficeMapScreenViewModel,
    onDismiss: () -> Unit,
    onItemSelected: (Any, OperationState) -> Unit,
) {
    val searchQuery = remember { mutableStateOf("") }
    val searchResults by viewModel.searchResults.collectAsState()

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
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Поиск",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                TextFields(
                    value = searchQuery.value,
                    text = "Введите запрос",
                    onvalChange = {
                        searchQuery.value = it
                        if (it.isNotBlank()) {
                            viewModel.searchItems(it)
                        }
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                if (searchQuery.value.isNotBlank()) {
                    if (searchResults.isNotEmpty()) {
                        LazyColumn(modifier = Modifier.weight(1f)) {
                            items(searchResults) { item ->
                                when (item) {
                                    is Workstation -> {
                                        SearchResultItem(
                                            title = item.employeeName,
                                            subtitle = item.position,
                                            number = item.number,
                                            onClick = {
                                                viewModel.viewModelScope.launch {
                                                    val floorState =
                                                        viewModel.determineFloorForWorkstation(item.id)
                                                    onItemSelected(item, floorState)
                                                }
                                            }
                                        )
                                    }

                                    is Wardrobe -> {
                                        SearchResultItem(
                                            title = item.wardrobeName,
                                            subtitle = item.content,
                                            number = "",
                                            onClick = {
                                                viewModel.viewModelScope.launch {
                                                    val floorState =
                                                        viewModel.determineFloorForWardrobe(item.id)
                                                    onItemSelected(item, floorState)
                                                }
                                            }
                                        )
                                    }
                                }
                                Divider(modifier = Modifier.padding(vertical = 8.dp))
                            }
                        }
                    } else {
                        Text(
                            text = "Ничего не найдено",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Gray,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                } else {
                    Text(
                        text = "Введите поисковый запрос",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}

