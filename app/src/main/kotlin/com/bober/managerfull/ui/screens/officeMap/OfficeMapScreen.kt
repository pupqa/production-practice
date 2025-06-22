package com.bober.managerfull.ui.screens.officeMap

//import com.hfad.codeinsideproba.OfficeViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import com.bober.managerfull.OfficeMapScreenViewModel
import com.bober.managerfull.OfficeViewModel
import com.bober.managerfull.R
import com.bober.managerfull.model.OperationState
import com.bober.managerfull.model.Workstation
import com.bober.managerfull.ui.components.mapScreen.EditInfoDialog
import com.bober.managerfull.ui.components.mapScreen.EmployedInfoDialog
import com.bober.managerfull.ui.components.mapScreen.NumberSelectionFab
import com.bober.managerfull.ui.components.mapScreen.WorkstationPoint
import com.bober.managerfull.ui.theme.Yellow

@Composable
fun OfficeMapScreen(
    navController: NavHostController,
    viewModel: OfficeMapScreenViewModel,
    viewModel2: OfficeViewModel,
) {
    var scale by remember { mutableStateOf(1f) }
    var offset by remember { mutableStateOf(Offset.Zero) }
    val coworking = viewModel.coworking.collectAsState()
    val floorThree = viewModel.floorThree.collectAsState()
    val floorFour = viewModel.floorFour.collectAsState()
    val floorSix = viewModel.floorSix.collectAsState()
    val conferenceFour = viewModel.conferenceFour.collectAsState()
    val conferenceSix = viewModel.conferenceSix.collectAsState()
    val floorState = viewModel.floorState.collectAsState()
    var show by remember { mutableStateOf(false) }
    var editName by remember { mutableStateOf("") }
    var editPosition by remember { mutableStateOf("") }
    val headerHeight = 100.dp
    val pointScale = (1f / scale).coerceIn(0.47f, 2f)
    var selectedWorkstation by remember { mutableStateOf<Workstation?>(null) }
    var selectedWorkstationForEdit by remember { mutableStateOf<Workstation?>(null) }


    val currentWorkstation = when (floorState.value) {
        OperationState.Coworking -> coworking.value?.workstations
        OperationState.FloorThree -> floorThree.value?.workstations
        OperationState.FloorFour -> floorFour.value?.workstations
        OperationState.FloorSix -> floorSix.value?.workstations
        OperationState.ConferenceFour -> conferenceFour.value?.workstations
        OperationState.ConferenceSix -> conferenceSix.value?.workstations
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


//    val title = when (floorState.value) {
//        OperationState.Coworking -> "Расписание: "
//        OperationState.FloorThree -> ""
//        OperationState.FloorFour -> ""
//        OperationState.FloorSix -> ""
//        OperationState.ConferenceFour -> "Расписание: "
//        OperationState.ConferenceSix -> "Расписание: "
//        else -> ""
//    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)) {
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .height(headerHeight)
                .background(Color.Black)
                .border(
                    width = 1.5.dp,
                    color = Color.Black.copy(alpha = 0.8f),
                )
                .zIndex(2f),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(16.dp))
                    .background(Color.Black)
                    .border(
                        width = 1.5.dp,
                        color = Yellow.copy(alpha = 0.8f),
                    )
            ) {
                Text(
                    text = floorTitle,
                    color = Yellow,
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = headerHeight)
                .pointerInput(Unit) {
                    detectTransformGestures { centroid, pan, zoom, rotation ->
                        val newScale = (scale * zoom).coerceIn(0.5f, 3f)

                        val maxOffsetX = if (newScale > 1f) {
                            (size.width * (newScale - 1)) * 0.5f
                        } else {
                            0f
                        }

                        val maxOffsetY = if (newScale > 1f) {
                            (size.height * (newScale - 1)) * 0.2f
                        } else {
                            0f
                        }

                        val newOffsetX = (offset.x + pan.x).coerceIn(-maxOffsetX, maxOffsetX)
                        val newOffsetY = (offset.y + pan.y).coerceIn(-maxOffsetY, maxOffsetY)

                        scale = newScale
                        offset = Offset(newOffsetX, newOffsetY)
                    }
                }
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(10.dp)
                    .fillMaxWidth()
                    .height(headerHeight),
                contentAlignment = Alignment.TopStart
            ) {
//                Text(
//                    text = title
//                )

            }

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
                when (floorState.value) {
                    OperationState.Coworking -> {
                        Image(
                            painter = painterResource(id = R.drawable.korvorking),
                            contentDescription = "Office Map",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier.fillMaxSize()
                        )

                        coworking.value?.workstations?.forEach { workstation: Workstation ->
                            WorkstationPoint(
                                workstation = workstation,
                                onClick = {
                                    selectedWorkstation = workstation
                                    selectedWorkstationForEdit = null
                                },
                                onLongClick = {
                                    selectedWorkstationForEdit = workstation
                                    selectedWorkstation = null
                                    show = true
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
                    }

                    OperationState.FloorThree -> {
                        Image(
                            painter = painterResource(id = R.drawable.flover3),
                            contentDescription = "Office Map",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier.fillMaxSize()
                        )

                        floorThree.value?.workstations?.forEach { workstation: Workstation ->
                            WorkstationPoint(
                                workstation = workstation,
                                onClick = {
                                    selectedWorkstation = workstation
                                    selectedWorkstationForEdit = null
                                },
                                onLongClick = {
                                    selectedWorkstationForEdit = workstation
                                    selectedWorkstation = null
                                    show = true
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
                    }

                    OperationState.FloorFour -> {
                        Image(
                            painter = painterResource(id = R.drawable.floor4),
                            contentDescription = "Office Map",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier.fillMaxSize()
                        )

                        floorFour.value?.workstations?.forEach { workstation: Workstation ->
                            WorkstationPoint(
                                workstation = workstation,
                                onClick = {
                                    selectedWorkstation = workstation
                                    selectedWorkstationForEdit = null
                                },
                                onLongClick = {
                                    selectedWorkstationForEdit = workstation
                                    selectedWorkstation = null
                                    show = true
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
                    }

                    OperationState.FloorSix -> {
                        Image(
                            painter = painterResource(id = R.drawable.floor6),
                            contentDescription = "Office Map",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier.fillMaxSize()
                        )

                        floorSix.value?.workstations?.forEach { workstation: Workstation ->
                            WorkstationPoint(
                                workstation = workstation,
                                onClick = {
                                    selectedWorkstation = workstation
                                    selectedWorkstationForEdit = null
                                },
                                onLongClick = {
                                    selectedWorkstationForEdit = workstation
                                    selectedWorkstation = null
                                    show = true
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
                    }

                    OperationState.ConferenceFour -> {
                        Image(
                            painter = painterResource(id = R.drawable.conference4),
                            contentDescription = "Office Map",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier.fillMaxSize()
                        )

                        conferenceFour.value?.workstations?.forEach { workstation: Workstation ->
                            WorkstationPoint(
                                workstation = workstation,
                                onClick = {
                                    selectedWorkstation = workstation
                                    selectedWorkstationForEdit = null
                                },
                                onLongClick = {
                                    selectedWorkstationForEdit = workstation
                                    selectedWorkstation = null
                                    show = true
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
                    }

                    OperationState.ConferenceSix -> {
                        Image(
                            painter = painterResource(id = R.drawable.conference6),
                            contentDescription = "Office Map",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier.fillMaxSize()
                        )

                        conferenceSix.value?.workstations?.forEach { workstation: Workstation ->
                            WorkstationPoint(
                                workstation = workstation,
                                onClick = {
                                    selectedWorkstation = workstation
                                    selectedWorkstationForEdit = null
                                },
                                onLongClick = {
                                    selectedWorkstationForEdit = workstation
                                    selectedWorkstation = null
                                    show = true
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
                    }
                }
            }
            if (show && selectedWorkstationForEdit != null) {
                EditInfoDialog(
                    title = selectedWorkstationForEdit?.employeeName ?: "",
                    description = selectedWorkstationForEdit?.position ?: "",
                    onDismiss = { show = false },
                    onTitle = { editName = it },
                    onDescription = { editPosition = it },
                    onSave = { name, position ->
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
                            position = position
                        )
                        show = false

                    })

            }
            NumberSelectionFab(
                modifier = Modifier.align(
                    Alignment.BottomEnd
                ),
                viewModel = viewModel,

                )
        }

        val upDateSelectedWorkstation = selectedWorkstation?.let { ws ->
            currentWorkstation?.find {
                it.id == ws.id
            }
        }
        upDateSelectedWorkstation?.let { workstation ->
            EmployedInfoDialog(
                workstation = workstation,
                onDismiss = { selectedWorkstation = null }
            )
        }
    }
}
