package leegroup.module.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import leegroup.module.data.util.orZero
import leegroup.module.domain.models.GitUserDetailModel

@Entity
@Serializable
data class GitUserDetail(
    @SerialName("id")
    @PrimaryKey
    val id: Int,

    @SerialName("login")
    @ColumnInfo(name = "login")
    val login: String,

    @SerialName("avatar_url")
    @ColumnInfo(name = "avatar_url")
    val avatarUrl: String?,

    @SerialName("html_url")
    @ColumnInfo(name = "html_url")
    val htmlUrl: String?,

    @SerialName("location")
    @ColumnInfo(name = "location")
    val location: String?,

    @SerialName("followers")
    @ColumnInfo(name = "followers")
    val followers: Int?,

    @SerialName("following")
    @ColumnInfo(name = "following")
    val following: Int?
)

fun GitUserDetail.mapToDomain() = GitUserDetailModel(
    id = id,
    login = login,
    avatarUrl = avatarUrl,
    htmlUrl = htmlUrl,
    location = location,
    followers = followers.orZero(),
    following = following.orZero()
)
