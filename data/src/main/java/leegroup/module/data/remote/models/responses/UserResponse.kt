package leegroup.module.data.remote.models.responses

import kotlinx.serialization.Serializable
import leegroup.module.data.models.User

@Serializable
data class UserResponse(
    val users: List<User>
)