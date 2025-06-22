package com.bober.managerfull

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bober.managerfull.model.FloorModel
import com.bober.managerfull.model.OperationState
import com.bober.managerfull.network.WorkstationService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class OfficeMapScreenViewModel : ViewModel() {
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

    private val _floorState = MutableStateFlow<OperationState>(OperationState.Coworking)
    val floorState = _floorState.asStateFlow()

    init {
        getCoworkingWorkstations()
        getFloorThreeWorkstation()
        getFloorFourWorkstation()
        getFloorSixWorkstation()
        getConferenceFourWorkstations()
        getConferenceSixWorkstations()
    }

    fun updateFloorState(operationState: OperationState) {
        viewModelScope.launch {
            _floorState.value = operationState
        }
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


    fun updateWorkstation(
        floorId: String,
        workstationId: String,
        employeeName: String,
        position: String,
    ) {
        viewModelScope.launch {
            try {
                Log.d(
                    "OfficeMapVM",
                    "Requesting update for $workstationId on $floorId\n" +
                            "Name: $employeeName, Position: $position"
                )

                WorkstationService().updateWorkstation(
                    floorId,
                    workstationId,
                    employeeName,
                    position
                )
                Log.d("OfficeMapVM", "Refresh data after update")
                refreshData()
            } catch (e: Exception) {
                Log.e("OfficeMapVM", "Error updating workstation", e)
            }
        }
    }

    private fun refreshData() {
        when (_floorState.value) {
            OperationState.Coworking -> getCoworkingWorkstations()
            OperationState.FloorThree -> getFloorThreeWorkstation()
            OperationState.FloorFour -> getFloorFourWorkstation()
            OperationState.FloorSix -> getFloorSixWorkstation()
            OperationState.ConferenceFour -> getConferenceFourWorkstations()
            OperationState.ConferenceSix -> getConferenceSixWorkstations()

        }
    }
}