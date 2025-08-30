package com.github.abhiram0106.lobotus_assignment.core.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MyErrorResponse(
    @SerialName("ErrorMessage") val message: String
)