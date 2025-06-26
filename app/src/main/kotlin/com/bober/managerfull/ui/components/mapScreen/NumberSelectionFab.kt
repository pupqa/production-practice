package com.bober.managerfull.ui.components.mapScreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.bober.managerfull.model.OperationState
import com.bober.managerfull.ui.screens.officeMap.OfficeMapScreenViewModel
import com.bober.managerfull.ui.theme.Gray
import com.bober.managerfull.ui.theme.Yellow

@Composable
fun NumberSelectionFab(
    modifier: Modifier = Modifier,
    viewModel: OfficeMapScreenViewModel,
) {

    var expanded by remember { mutableStateOf(false) }
    val floorState by viewModel.floorState.collectAsState()
    val isLoading = remember {
        derivedStateOf {
            when (floorState) {
                OperationState.Coworking -> viewModel.coworking.value == null
                OperationState.FloorThree -> viewModel.floorThree.value == null
                OperationState.FloorFour -> viewModel.floorFour.value == null
                OperationState.FloorSix -> viewModel.floorSix.value == null
                OperationState.ConferenceFour -> viewModel.conferenceFour.value == null
                OperationState.ConferenceSix -> viewModel.conferenceSix.value == null
                else -> false
            }
        }
    }.value

    Column(
        horizontalAlignment = Alignment.End,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Bottom
    ) {
        FloatingActionButton(
            onClick = { expanded = !expanded },
            containerColor = Yellow,
            modifier = modifier.padding(bottom = 8.dp)
        ) {
            Icon(
                imageVector = if (expanded) Icons.Default.Close else Icons.Default.KeyboardArrowUp,
                contentDescription = if (expanded) "Закрыть" else "Открыть меню",
                tint = Color.Black
            )
        }

        AnimatedVisibility(
            visible = expanded,
            enter = fadeIn() + expandVertically(expandFrom = Alignment.Bottom),
            exit = fadeOut() + shrinkVertically(shrinkTowards = Alignment.Bottom)
        ) {
            Column(
                horizontalAlignment = Alignment.End,
                modifier = Modifier.padding(bottom = 8.dp)
            ) {
                listOf(
                    OperationState.Coworking to "К",
                    OperationState.FloorThree to "3",
                    OperationState.FloorFour to "4",
                    OperationState.FloorSix to "6",
                    OperationState.ConferenceFour to "П4",
                    OperationState.ConferenceSix to "П6"
                ).forEach { (state, text) ->
                    FloatingActionButtonFun(
                        text = text,
                        color = if (floorState == state) Gray else Color.White,
                        onClick = { viewModel.updateFloorState(state) },
                        enabled = !isLoading
                    )
                }
            }
        }
    }
}