package leegroup.module.data.models

import androidx.annotation.Keep
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import leegroup.module.domain.models.UserModel

@Serializable
@Entity
@Keep
data class User(
    @PrimaryKey
    @SerialName("id")
    val id: Int,

    @SerialName("firstName")
    @ColumnInfo(name = "first_name")
    val firstName: String?,

    @ColumnInfo(name = "last_name")
    @SerialName("lastName")
    val lastName: String?,
)

fun User.mapToDomain() = UserModel(
    id = id,
    firstName = firstName,
    lastName = lastName,
)

