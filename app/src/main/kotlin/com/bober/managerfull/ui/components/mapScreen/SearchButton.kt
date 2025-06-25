package com.bober.managerfull.ui.components.mapScreen

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SavedSearch
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.bober.managerfull.ui.screens.officeMap.OfficeMapScreenViewModel
import com.bober.managerfull.ui.theme.Yellow

@Composable
fun SearchButton(
    modifier: Modifier = Modifier,
    viewModel: OfficeMapScreenViewModel,
    onClick: () -> Unit,
) {
    FloatingActionButton(
        onClick = onClick,
        containerColor = Yellow,
        modifier = modifier.padding(bottom = 20.dp, start = 12.dp)
    ) {
        Icon(
            imageVector = Icons.Default.SavedSearch,
            contentDescription = "Открыть поиск",
            tint = Color.Black
        )
    }
}