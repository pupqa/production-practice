package com.bober.managerfull.model

import kotlinx.serialization.Serializable

@Serializable
data class Workstation(
    val id: String = "",
    val x: Float = 0f,
    val y: Float = 0f,
    val employeeName: String = "",
    val position: String = "",
    val isBusy: Boolean = false,
)