package com.vpigadas.ecosity.network.models

import java.util.*

data class PantBasingResponse(
    val id: String,
    val basin: String,
    val nameel: String,
    val nameen: String
) {
    fun getName(): String = when (Locale.getDefault()) {
        Locale("el", "GR") -> nameel
        else -> nameen
    }
}
