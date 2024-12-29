package com.app.androidcompose

import com.app.androidcompose.ui.base.ErrorState
import leegroup.module.domain.exceptions.ApiException
import leegroup.module.domain.models.Error
import leegroup.module.domain.models.UserModel

object MockUtil {
    const val apiErrorMessage = "Something went wrong"

    val apiErrorState = ErrorState.Api(message = apiErrorMessage)

    val apiError = ApiException(
        error = Error(message = apiErrorMessage),
        httpCode = 404,
        httpMessage = "Not Found"
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
}