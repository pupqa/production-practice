package com.bober.managerfull.model

import kotlinx.serialization.Serializable

@Serializable
data class FloorModel(
    val floorName: String = "",
    val workstations: List<Workstation> = listOf(),
)
