package com.bober.manager.data.local

import kotlinx.serialization.Serializable

@Serializable
data class MainScreenDataObject(
    val uid: String = "",
    val email: String = "",
    val name: String,
)