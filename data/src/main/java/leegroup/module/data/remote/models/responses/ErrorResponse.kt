package leegroup.module.data.remote.models.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import leegroup.module.domain.models.Error

@Serializable
data class ErrorResponse(
    @SerialName("message")
    val message: String,
    @SerialName("code")
    val code: Int,
)

fun ErrorResponse.mapToError() = Error(
    message = message,
    code = code
)
