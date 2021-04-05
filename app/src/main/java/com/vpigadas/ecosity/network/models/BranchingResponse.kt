package com.vpigadas.ecosity.network.models

import java.util.*

data class BranchingResponse(
    val id: String,
    val branch: String,
    val nameel: String,
    val nameen: String
) {
    fun getName(): String = when (Locale.getDefault()) {
        Locale("el", "GR") -> nameel
        else -> nameen
    }
}
