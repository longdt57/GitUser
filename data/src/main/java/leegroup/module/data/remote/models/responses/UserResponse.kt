package leegroup.module.data.remote.models.responses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import leegroup.module.data.models.User

@Serializable
data class UserResponse(
    @SerialName("users")
    val users: List<User>
)