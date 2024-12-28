package leegroup.module.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import leegroup.module.domain.models.GitUserModel

@Entity
@Serializable
data class GitUser(

    @PrimaryKey
    @SerialName("id")
    val id: Long,

    @SerialName("login")
    val login: String,

    @SerialName("avatar_url")
    @ColumnInfo(name = "avatar_url")
    val avatarUrl: String?,

    @SerialName("html_url")
    @ColumnInfo(name = "html_url")
    val htmlUrl: String?,
)

fun GitUser.mapToDomain() = GitUserModel(
    id = id,
    login = login,
    avatarUrl = avatarUrl,
    htmlUrl = htmlUrl,
)

fun List<GitUser>.mapToDomain() = map { it.mapToDomain() }
