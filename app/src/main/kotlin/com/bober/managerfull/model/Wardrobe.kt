package com.bober.managerfull.model

import kotlinx.serialization.Serializable

@Serializable
data class Wardrobe(
    val id: String = "",
    val x: Float = 0f,
    val y: Float = 0f,
    val wardrobeName: String = "",
    val content: String = "",
    val isBusy: Boolean = false,
    val number: String = "",
    val additionalFields: List<String> = emptyList() // Для хранения доп. полей
)