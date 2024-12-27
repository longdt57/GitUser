package leegroup.module.data.remote.models.responses

import androidx.annotation.Keep
import kotlinx.serialization.Serializable
import leegroup.module.data.models.User

@Serializable
@Keep
data class UserResponse(
    val users: List<User>
)