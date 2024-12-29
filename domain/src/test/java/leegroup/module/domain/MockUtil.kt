package leegroup.module.domain

import leegroup.module.domain.exceptions.ApiException
import leegroup.module.domain.models.Error
import leegroup.module.domain.models.GitUserDetailModel
import leegroup.module.domain.models.UserModel

object MockUtil {

    val apiException = ApiException(
        error = Error(
            message = "message",
        ),
        httpCode = 400,
        httpMessage = "Bad Request "
    )

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

    const val login = "longdt57"
    val gitUserDetail = GitUserDetailModel(
        id = 8809113,
        login = login,
        name = "Logan Do",
        avatarUrl = "https://avatars.githubusercontent.com/u/8809113?v=4",
        blog = "https://github.com/longdt57",
        location = "Hanoi",
        followers = 100_000,
        following = 50_000,
    )
}