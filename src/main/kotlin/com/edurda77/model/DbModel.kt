package com.edurda77.model

import kotlinx.serialization.Serializable


@Serializable
data class DbModel(
    val login : String,
    val password: String,
    val lastData: Int,
    val currentData: Int
)
