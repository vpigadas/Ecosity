package com.vpigadas.ecosity.network.models

import java.util.*

data class ΤrunkSurfaceResponse(
    val id: String,
    val surface: String,
    val nameel: String,
    val nameen: String
) {
    fun getName(): String = when (Locale.getDefault()) {
        Locale("el", "GR") -> nameel
        else -> nameen
    }
}
