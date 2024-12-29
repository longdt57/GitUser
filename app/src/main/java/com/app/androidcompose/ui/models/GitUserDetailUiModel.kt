package com.app.androidcompose.ui.models

import androidx.compose.runtime.Immutable

@Immutable
data class GitUserDetailUiModel(
    val login: String = "",
    val name: String = "",
    val avatarUrl: String = "",
    val blog: String = "",
    val location: String = "",
    val followers: String = "",
    val following: String = "",
)
