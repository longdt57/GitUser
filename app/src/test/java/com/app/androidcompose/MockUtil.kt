package com.app.androidcompose

import leegroup.module.domain.models.UserModel

object MockUtil {

    val userModels = listOf(
        UserModel(
            id = 1,
            firstName = "Logan",
            lastName = "Do"
        ),
        UserModel(
            id = 2,
            firstName = "Nick",
            lastName = "Do"
        )
    )
}