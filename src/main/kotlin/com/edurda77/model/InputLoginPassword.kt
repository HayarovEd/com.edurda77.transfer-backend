package com.edurda77.model
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class InputLoginPassword(
    @SerialName("login")
    val login: String,
    @SerialName("password")
    val password: String,

)
