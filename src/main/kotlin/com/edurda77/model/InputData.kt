package com.edurda77.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InputData(
    @SerialName("login")
    val transferLogin: String,
    @SerialName("password")
    val transferPassword: String,
    @SerialName("data")
    val transferData: Int
)
