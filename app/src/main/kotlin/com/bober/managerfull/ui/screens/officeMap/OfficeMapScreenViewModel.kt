// OfficeMapScreenViewModel.kt
package com.bober.managerfull.ui.screens.officeMap

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bober.managerfull.model.FloorModel
import com.bober.managerfull.model.FloorModelWardrobe
import com.bober.managerfull.model.OperationState
import com.bober.managerfull.network.WorkstationService
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class OfficeMapScreenViewModel : ViewModel() {
    // Состояния для рабочих мест
    private val _coworking = MutableStateFlow<FloorModel?>(null)
    val coworking = _coworking.asStateFlow()

    private val _floorThree = MutableStateFlow<FloorModel?>(null)
    val floorThree = _floorThree.asStateFlow()

    private val _floorFour = MutableStateFlow<FloorModel?>(null)
    val floorFour = _floorFour.asStateFlow()

    private val _floorSix = MutableStateFlow<FloorModel?>(null)
    val floorSix = _floorSix.asStateFlow()

    private val _conferenceFour = MutableStateFlow<FloorModel?>(null)
    val conferenceFour = _conferenceFour.asStateFlow()

    private val _conferenceSix = MutableStateFlow<FloorModel?>(null)
    val conferenceSix = _conferenceSix.asStateFlow()

    // Состояния для шкафов
    private val _coworkingW = MutableStateFlow<FloorModelWardrobe?>(null)
    val coworkingW = _coworkingW.asStateFlow()

    private val _floorThreeW = MutableStateFlow<FloorModelWardrobe?>(null)
    val floorThreeW = _floorThreeW.asStateFlow()

    private val _floorFourW = MutableStateFlow<FloorModelWardrobe?>(null)
    val floorFourW = _floorFourW.asStateFlow()

    private val _floorSixW = MutableStateFlow<FloorModelWardrobe?>(null)
    val floorSixW = _floorSixW.asStateFlow()

    private val _conferenceFourW = MutableStateFlow<FloorModelWardrobe?>(null)
    val conferenceFourW = _conferenceFourW.asStateFlow()

    private val _conferenceSixW = MutableStateFlow<FloorModelWardrobe?>(null)
    val conferenceSixW = _conferenceSixW.asStateFlow()

    private val _floorState = MutableStateFlow<OperationState>(OperationState.Coworking)
    val floorState = _floorState.asStateFlow()

    init {
        loadWorkstations()
        loadWardrobes()
    }

    fun updateFloorState(operationState: OperationState) {
        viewModelScope.launch {
            _floorState.value = operationState
        }
    }

    private fun loadWorkstations() {
        getCoworkingWorkstations()
        getFloorThreeWorkstation()
        getFloorFourWorkstation()
        getFloorSixWorkstation()
        getConferenceFourWorkstations()
        getConferenceSixWorkstations()
    }

    private fun loadWardrobes() {
        getCoworkingWardrobe()
        getFloorThreeWardrobe()
        getFloorFourWardrobe()
        getFloorSixWardrobe()
        getConferenceFourWardrobe()
        getConferenceSixWardrobe()
    }

    fun getCoworkingWorkstations() {
        viewModelScope.launch {
            _coworking.value = WorkstationService().getCoworkingWorkStations()
        }
    }

    fun getFloorThreeWorkstation() {
        viewModelScope.launch {
            _floorThree.value = WorkstationService().getFloorThreeWorkStation()
        }
    }

    fun getFloorFourWorkstation() {
        viewModelScope.launch {
            _floorFour.value = WorkstationService().getFloorFourWorkStation()
        }
    }

    fun getFloorSixWorkstation() {
        viewModelScope.launch {
            _floorSix.value = WorkstationService().getFloorSixWorkStation()
        }
    }

    fun getConferenceFourWorkstations() {
        viewModelScope.launch {
            _conferenceFour.value = WorkstationService().getConferenceFourWorkStations()
        }
    }

    fun getConferenceSixWorkstations() {
        viewModelScope.launch {
            _conferenceSix.value = WorkstationService().getConferenceSixWorkStations()
        }
    }

    fun getCoworkingWardrobe() {
        viewModelScope.launch {
            _coworkingW.value = WorkstationService().getCoworkingWardrobe()
        }
    }

    fun getFloorThreeWardrobe() {
        viewModelScope.launch {
            _floorThreeW.value = WorkstationService().getFloorThreeWardrobe()
        }
    }

    fun getFloorFourWardrobe() {
        viewModelScope.launch {
            _floorFourW.value = WorkstationService().getFloorFourWardrobe()
        }
    }

    fun getFloorSixWardrobe() {
        viewModelScope.launch {
            _floorSixW.value = WorkstationService().getFloorSixWardrobe()
        }
    }

    fun getConferenceFourWardrobe() {
        viewModelScope.launch {
            _conferenceFourW.value = WorkstationService().getConferenceFourWardrobe()
        }
    }

    fun getConferenceSixWardrobe() {
        viewModelScope.launch {
            _conferenceSixW.value = WorkstationService().getConferenceSixWardrobe()
        }
    }

    fun updateWorkstation(
        floorId: String,
        workstationId: String,
        employeeName: String,
        position: String,
        number: String = ""
    ) {
        viewModelScope.launch {
            try {
                WorkstationService().updateWorkstation(
                    floorId,
                    workstationId,
                    employeeName,
                    position,
                    number
                )
                refreshData()
            } catch (e: Exception) {
                Log.e("OfficeMapVM", "Error updating workstation", e)
            }
        }
    }
    fun updateWardrobe(
        floorId: String,
        wardrobeId: String,
        wardrobeName: String,
        content: String,
        additionalFields: List<String> = emptyList()
    ) {
        viewModelScope.launch {
            try {
                WorkstationService().updateWardrobe(
                    floorId,
                    wardrobeId,
                    wardrobeName,
                    content,
                    additionalFields
                )
                refreshData()
            } catch (e: Exception) {
                Log.e("OfficeMapVM", "Error updating wardrobe", e)
            }
        }
    }

    private fun refreshData() {
        viewModelScope.launch {
            delay(300)

            when (_floorState.value) {
                OperationState.Coworking -> {
                    getCoworkingWorkstations()
                    getCoworkingWardrobe()
                }

                OperationState.FloorThree -> {
                    getFloorThreeWorkstation()
                    getFloorThreeWardrobe()
                }

                OperationState.FloorFour -> {
                    getFloorFourWorkstation()
                    getFloorFourWardrobe()
                }

                OperationState.FloorSix -> {
                    getFloorSixWorkstation()
                    getFloorSixWardrobe()
                }

                OperationState.ConferenceFour -> {
                    getConferenceFourWorkstations()
                    getConferenceFourWardrobe()
                }

                OperationState.ConferenceSix -> {
                    getConferenceSixWorkstations()
                    getConferenceSixWardrobe()
                }
            }
        }
    }

}