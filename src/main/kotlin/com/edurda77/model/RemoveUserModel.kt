package com.edurda77.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoveUserModel(
    @SerialName("login")
    val login: String,
    @SerialName("password")
    val password: String,
    @SerialName("user")
    val user: String,
)
