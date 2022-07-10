package com.edurda77.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddUserModel(
    @SerialName("login")
    val login: String,
    @SerialName("password")
    val password: String,
    @SerialName("user")
    val user: String,
    @SerialName("userPassword")
    val userPassword: String,
)
