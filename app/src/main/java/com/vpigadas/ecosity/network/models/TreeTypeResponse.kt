package com.vpigadas.ecosity.network.models

import java.util.*

data class TreeTypeResponse(
    val id: String,
    val type: String,
    val nameel: String,
    val nameen: String
) {
    fun getName(): String = when (Locale.getDefault()) {
        Locale("el", "GR") -> nameel
        else -> nameen
    }
}