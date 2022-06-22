package com.edurda77.model
import kotlinx.serialization.Serializable


@Serializable
data class InputLoginPassword(

    val inputLogin : String,
    val inputPassword: String
)
