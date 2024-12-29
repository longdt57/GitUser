package leegroup.module.data

import io.mockk.every
import io.mockk.mockk
import leegroup.module.data.models.GitUser
import leegroup.module.data.models.GitUserDetail
import leegroup.module.data.models.User
import leegroup.module.domain.models.Error
import leegroup.module.domain.models.GitUserDetailModel
import leegroup.module.domain.models.GitUserModel
import leegroup.module.domain.models.UserModel
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response

object MockUtil {

    val mockHttpException: HttpException
        get() {
            val response = mockk<Response<Any>>()
            val httpException = mockk<HttpException>()
            val responseBody = mockk<ResponseBody>()
            every { response.code() } returns 500
            every { response.message() } returns "message"
            every { response.errorBody() } returns responseBody
            every { httpException.code() } returns response.code()
            every { httpException.message() } returns response.message()
            every { httpException.response() } returns response
            every { responseBody.string() } returns
                """
                    {
                        "message": "message"
                    }
                """.trimIndent()
            return httpException
        }

    val error = Error(
        message = "message"
    )

    val users = listOf(
        User(
            id = 1,
            firstName = "Logan",
            lastName = "Do"
        ),
        User(
            id = 2,
            firstName = "Nick",
            lastName = "Do"
        )
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

    val gitUserDetail = GitUserDetail(
        id = 8809113,
        login = login,
        name = "Logan Do",
        avatarUrl = "https://avatars.githubusercontent.com/u/8809113?v=4",
        blog = "https://github.com/longdt57",
        location = "Hanoi",
        followers = 100_000,
        following = 50_000
    )

    val gitUserDetailModel = GitUserDetailModel(
        id = 8809113,
        login = login,
        name = "Logan Do",
        avatarUrl = "https://avatars.githubusercontent.com/u/8809113?v=4",
        blog = "https://github.com/longdt57",
        location = "Hanoi",
        followers = 100_000,
        following = 50_000,
    )

    const val since = 0
    const val perPage = 3

    val sampleGitUsers = listOf(
        GitUser(
            id = 1,
            login = "longdt57",
            avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4",
            htmlUrl = "https://github.com/longdt57"
        ),
        GitUser(
            id = 2,
            login = "defunkt",
            avatarUrl = "https://avatars.githubusercontent.com/u/2?v=4",
            htmlUrl = "https://github.com/defunkt"
        ),
        GitUser(
            id = 3,
            login = "pjhyett",
            avatarUrl = "https://avatars.githubusercontent.com/u/3?v=4",
            htmlUrl = "https://github.com/pjhyett"
        )
    )

    val sampleGitUserModels = listOf(
        GitUserModel(
            id = 1,
            login = "longdt57",
            avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4",
            htmlUrl = "https://github.com/longdt57"
        ),
        GitUserModel(
            id = 2,
            login = "defunkt",
            avatarUrl = "https://avatars.githubusercontent.com/u/2?v=4",
            htmlUrl = "https://github.com/defunkt"
        ),
        GitUserModel(
            id = 3,
            login = "pjhyett",
            avatarUrl = "https://avatars.githubusercontent.com/u/3?v=4",
            htmlUrl = "https://github.com/pjhyett"
        )
    )
}