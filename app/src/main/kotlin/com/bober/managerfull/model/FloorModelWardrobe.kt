package com.bober.managerfull.model

import kotlinx.serialization.Serializable

@Serializable
data class FloorModelWardrobe (
    val floorName: String = "",
    val wardrobe: List<Wardrobe> = listOf(),
)