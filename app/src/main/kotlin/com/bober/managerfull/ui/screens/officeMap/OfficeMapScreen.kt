package com.bober.managerfull.ui.screens.officeMap

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import com.bober.managerfull.OfficeViewModel
import com.bober.managerfull.R
import com.bober.managerfull.model.FloorModel
import com.bober.managerfull.model.FloorModelWardrobe
import com.bober.managerfull.model.OperationState
import com.bober.managerfull.model.Wardrobe
import com.bober.managerfull.model.Workstation
import com.bober.managerfull.ui.components.mapScreen.EditInfoDialog
import com.bober.managerfull.ui.components.mapScreen.EditInfoDialogWardrobe
import com.bober.managerfull.ui.components.mapScreen.EmployedInfoDialog
import com.bober.managerfull.ui.components.mapScreen.NumberSelectionFab
import com.bober.managerfull.ui.components.mapScreen.SearchButton
import com.bober.managerfull.ui.components.mapScreen.WardrobeInfoDialog
import com.bober.managerfull.ui.components.mapScreen.WardrobePoint
import com.bober.managerfull.ui.components.mapScreen.WorkstationPoint
import com.bober.managerfull.ui.theme.Yellow

@Composable
fun OfficeMapScreen(
    navController: NavHostController,
    viewModel: OfficeMapScreenViewModel,
    viewModel2: OfficeViewModel,
) {
    var scale by remember { mutableFloatStateOf(1f) }
    var offset by remember { mutableStateOf(Offset.Zero) }

    // Состояния для рабочих мест
    val coworking = viewModel.coworking.collectAsState()
    val floorThree = viewModel.floorThree.collectAsState()
    val floorFour = viewModel.floorFour.collectAsState()
    val floorSix = viewModel.floorSix.collectAsState()
    val conferenceFour = viewModel.conferenceFour.collectAsState()
    val conferenceSix = viewModel.conferenceSix.collectAsState()

    // Состояния для шкафов
    val coworkingW = viewModel.coworkingW.collectAsState()
    val floorThreeW = viewModel.floorThreeW.collectAsState()
    val floorFourW = viewModel.floorFourW.collectAsState()
    val floorSixW = viewModel.floorSixW.collectAsState()
    val conferenceFourW = viewModel.conferenceFourW.collectAsState()
    val conferenceSixW = viewModel.conferenceSixW.collectAsState()

    val floorState = viewModel.floorState.collectAsState()
    val headerHeight = 120.dp
    val pointScale = (1f / scale).coerceIn(0.47f, 2f)

    // Состояния для выбранных элементов
    var selectedWorkstation by remember { mutableStateOf<Workstation?>(null) }
    var selectedWorkstationForEdit by remember { mutableStateOf<Workstation?>(null) }
    var selectedWardrobe by remember { mutableStateOf<Wardrobe?>(null) }
    var showEditDialog by remember { mutableStateOf(false) }
    var editName by remember { mutableStateOf("") }
    var editPosition by remember { mutableStateOf("") }


    var selectedWardrobeForEdit by remember { mutableStateOf<Wardrobe?>(null) }
    var showWardrobeEditDialog by remember { mutableStateOf(false) }
    var editWardrobeName by remember { mutableStateOf("") }
    var editWardrobeContent by remember { mutableStateOf("") }


    // Получаем текущие рабочие места и шкафы для активного этажа
    val currentWorkstations = when (floorState.value) {
        OperationState.Coworking -> coworking.value?.workstations
        OperationState.FloorThree -> floorThree.value?.workstations
        OperationState.FloorFour -> floorFour.value?.workstations
        OperationState.FloorSix -> floorSix.value?.workstations
        OperationState.ConferenceFour -> conferenceFour.value?.workstations
        OperationState.ConferenceSix -> conferenceSix.value?.workstations
        else -> null
    }

    val currentWardrobes = when (floorState.value) {
        OperationState.Coworking -> coworkingW.value?.wardrobe
        OperationState.FloorThree -> floorThreeW.value?.wardrobe
        OperationState.FloorFour -> floorFourW.value?.wardrobe
        OperationState.FloorSix -> floorSixW.value?.wardrobe
        OperationState.ConferenceFour -> conferenceFourW.value?.wardrobe
        OperationState.ConferenceSix -> conferenceSixW.value?.wardrobe
        else -> null
    }

    val floorTitle = when (floorState.value) {
        OperationState.Coworking -> "Коворкинг"
        OperationState.FloorThree -> "3 Этаж"
        OperationState.FloorFour -> "4 Этаж"
        OperationState.FloorSix -> "6 Этаж"
        OperationState.ConferenceFour -> "Переговорка 4 этаж"
        OperationState.ConferenceSix -> "Переговорка 6 этаж"
        else -> ""
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Заголовок этажа
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .height(headerHeight)
                .background(Color.Black)
                .zIndex(2f),
            contentAlignment = Alignment.BottomCenter
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            Box(
                modifier = Modifier
                    .padding(bottom = 20.dp)
                    .clip(shape = RoundedCornerShape(2.dp))
                    .background(Color.Black)
                    .border(width = 1.5.dp, color = Yellow.copy(alpha = 0.8f))
            ) {
                Text(
                    text = floorTitle,
                    color = Yellow,
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                )
            }
        }

        // Основное содержимое карты
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = headerHeight)
                .pointerInput(Unit) {
                    detectTransformGestures { centroid, pan, zoom, rotation ->
                        val newScale = (scale * zoom).coerceIn(0.5f, 3f)
                        val maxOffsetX =
                            if (newScale > 1f) (size.width * (newScale - 1)) * 0.5f else 0f
                        val maxOffsetY =
                            if (newScale > 1f) (size.height * (newScale - 1)) * 0.2f else 0f
                        val newOffsetX = (offset.x + pan.x).coerceIn(-maxOffsetX, maxOffsetX)
                        val newOffsetY = (offset.y + pan.y).coerceIn(-maxOffsetY, maxOffsetY)
                        scale = newScale
                        offset = Offset(newOffsetX, newOffsetY)
                    }
                }
        ) {
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer(
                        scaleX = scale,
                        scaleY = scale,
                        translationX = offset.x,
                        translationY = offset.y
                    )
            ) {
                // Отображение фона карты в зависимости от этажа
                when (floorState.value) {
                    OperationState.Coworking -> {
                        Image(
                            painter = painterResource(id = R.drawable.korvorking),
                            contentDescription = "Office Map",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    OperationState.FloorThree -> {
                        Image(
                            painter = painterResource(id = R.drawable.flover3),
                            contentDescription = "Office Map",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    OperationState.FloorFour -> {
                        Image(
                            painter = painterResource(id = R.drawable.floor4),
                            contentDescription = "Office Map",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    OperationState.FloorSix -> {
                        Image(
                            painter = painterResource(id = R.drawable.floor6),
                            contentDescription = "Office Map",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    OperationState.ConferenceFour -> {
                        Image(
                            painter = painterResource(id = R.drawable.conference4),
                            contentDescription = "Office Map",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    OperationState.ConferenceSix -> {
                        Image(
                            painter = painterResource(id = R.drawable.conference6),
                            contentDescription = "Office Map",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }

                // Отображение рабочих мест
                currentWorkstations?.forEach { workstation ->
                    WorkstationPoint(
                        workstation = workstation,
                        onClick = {
                            selectedWorkstation = workstation
                            selectedWorkstationForEdit = null
                            selectedWardrobe = null
                        },
                        onLongClick = {
                            selectedWorkstationForEdit = workstation
                            selectedWorkstation = null
                            selectedWardrobe = null
                            showEditDialog = true
                        },
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .offset(
                                x = (workstation.x.toFloat() * maxWidth.value).dp,
                                y = (workstation.y.toFloat() * maxHeight.value).dp
                            )
                            .graphicsLayer {
                                scaleX = pointScale
                                scaleY = pointScale
                            }
                    )
                }

                currentWardrobes?.forEach { wardrobe ->
                    WardrobePoint(
                        wardrobe = wardrobe,
                        onClick = {
                            selectedWardrobe = wardrobe
                            selectedWorkstation = null
                            selectedWorkstationForEdit = null
                        },
                        onLongClick = {
                            selectedWardrobeForEdit = wardrobe
                            selectedWardrobe = null
                            selectedWorkstation = null
                            showWardrobeEditDialog = true
                        },
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .offset(
                                x = (wardrobe.x.toFloat() * maxWidth.value).dp,
                                y = (wardrobe.y.toFloat() * maxHeight.value).dp
                            )
                            .graphicsLayer {
                                scaleX = pointScale
                                scaleY = pointScale
                            }
                    )
                }

                // Кнопка выбора этажа
                NumberSelectionFab(
                    modifier = Modifier.align(Alignment.BottomEnd),
                    viewModel = viewModel,
                )
                SearchButton(
                    modifier = Modifier.align(Alignment.BottomStart),
                    viewModel = viewModel,
                )
            }

            if (showEditDialog && selectedWorkstationForEdit != null) {
                EditInfoDialog(
                    title = selectedWorkstationForEdit?.employeeName ?: "",
                    description = selectedWorkstationForEdit?.position ?: "",
                    data = selectedWorkstationForEdit?.number ?: "", // Передаем текущий номер
                    onDismiss = { showEditDialog = false },
                    onTitle = { editName = it },
                    onDescription = { editPosition = it },
                    onData = { /* Можно сохранить номер в состоянии, если нужно */ },
                    onSave = { name, position, number ->
                        viewModel.updateWorkstation(
                            floorId = when (floorState.value) {
                                OperationState.Coworking -> "coworking"
                                OperationState.FloorThree -> "floor3"
                                OperationState.FloorFour -> "floor4"
                                OperationState.FloorSix -> "floor6"
                                OperationState.ConferenceFour -> "conference4"
                                OperationState.ConferenceSix -> "conference6"
                                else -> ""
                            },
                            workstationId = selectedWorkstationForEdit?.id ?: "",
                            employeeName = name,
                            position = position,
                            number = number // Передаем номер
                        )
                        showEditDialog = false
                    }
                )
            }

            if (showWardrobeEditDialog && selectedWardrobeForEdit != null) {
                EditInfoDialogWardrobe(
                    title = selectedWardrobeForEdit?.wardrobeName ?: "",
                    description = selectedWardrobeForEdit?.content ?: "",
                    initialAdditionalFields = selectedWardrobeForEdit?.additionalFields
                        ?: emptyList(),
                    onDismiss = { showWardrobeEditDialog = false },
                    onTitle = { editWardrobeName = it },
                    onDescription = { editWardrobeContent = it },
                    onSave = { name, content, additionalFields ->
                        viewModel.updateWardrobe(
                            floorId = when (floorState.value) {
                                OperationState.Coworking -> "coworking"
                                OperationState.FloorThree -> "floor3"
                                OperationState.FloorFour -> "floor4"
                                OperationState.FloorSix -> "floor6"
                                OperationState.ConferenceFour -> "conference4"
                                OperationState.ConferenceSix -> "conference6"
                                else -> ""
                            },
                            wardrobeId = selectedWardrobeForEdit?.id ?: "",
                            wardrobeName = name,
                            content = content,
                            additionalFields = additionalFields
                        )
                    }
                )
            }
            selectedWorkstation?.let { workstation ->
                val updatedWorkstation = currentWorkstations?.find { it.id == workstation.id } ?: workstation
                EmployedInfoDialog(
                    workstation = updatedWorkstation,
                    onDismiss = { selectedWorkstation = null },
                    onEditInventory = { newNumber ->
                        viewModel.updateWorkstation(
                            floorId = when (floorState.value) {
                                OperationState.Coworking -> "coworking"
                                OperationState.FloorThree -> "floor3"
                                OperationState.FloorFour -> "floor4"
                                OperationState.FloorSix -> "floor6"
                                OperationState.ConferenceFour -> "conference4"
                                OperationState.ConferenceSix -> "conference6"
                                else -> ""
                            },
                            workstationId = updatedWorkstation.id,
                            employeeName = updatedWorkstation.employeeName,
                            position = updatedWorkstation.position,
                            number = newNumber
                        )
                    }
                )
            }

            selectedWardrobe?.let { wardrobe ->
                val updatedWardrobe = currentWardrobes?.find { it.id == wardrobe.id } ?: wardrobe
                WardrobeInfoDialog(
                    wardrobe = updatedWardrobe,
                    onDismiss = { selectedWardrobe = null },
                    onEditInventory = { newInventoryNumber ->
                        viewModel.updateWardrobe(
                            floorId = when (floorState.value) {
                                OperationState.Coworking -> "coworking"
                                OperationState.FloorThree -> "floor3"
                                OperationState.FloorFour -> "floor4"
                                OperationState.FloorSix -> "floor6"
                                OperationState.ConferenceFour -> "conference4"
                                OperationState.ConferenceSix -> "conference6"
                                else -> ""
                            },
                            wardrobeId = updatedWardrobe.id,
                            wardrobeName = newInventoryNumber,
                            content = updatedWardrobe.content,
                            additionalFields = updatedWardrobe.additionalFields
                        )
                    }
                )
            }
        }
    }
}