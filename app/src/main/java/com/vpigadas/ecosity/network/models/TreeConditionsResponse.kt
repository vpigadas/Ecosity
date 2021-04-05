package com.vpigadas.ecosity.network.models

import java.util.*

data class TreeConditionsResponse(
    val id: String,
    val condition: String,
    val nameel: String,
    val nameen: String
) {
    fun getName(): String = when (Locale.getDefault()) {
        Locale("el", "GR") -> nameel
        else -> nameen
    }
}
