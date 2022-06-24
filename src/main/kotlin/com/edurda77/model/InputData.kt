package com.edurda77.model

import kotlinx.serialization.Serializable

@Serializable
data class InputData(
    val transferLogin: String,
    val transferData: Int
)
