package leegroup.module.domain.models

data class GitUserDetailModel(
    val id: Int,
    val login: String,
    val name: String?,
    val avatarUrl: String?,
    val blog: String?,
    val location: String?,
    val followers: Int,
    val following: Int,
)