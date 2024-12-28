package leegroup.module.domain.models

data class GitUserDetailModel(
    val id: Int,
    val login: String,
    val avatarUrl: String?,
    val htmlUrl: String?,
    val location: String?,
    val followers: Int,
    val following: Int
)