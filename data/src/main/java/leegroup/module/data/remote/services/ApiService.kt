package leegroup.module.data.remote.services

import leegroup.module.data.models.GitUser
import leegroup.module.data.models.GitUserDetail
import leegroup.module.data.remote.models.responses.UserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("https://dummyjson.com/users")
    suspend fun getUser(): UserResponse

    @GET("users")
    suspend fun getGitUser(
        @Query("since") since: Int,
        @Query("per_page") perPage: Int,
    ): List<GitUser>

    @GET("users/{login}")
    suspend fun getGitUserDetail(
        @Query("login") login: String,
    ): GitUserDetail
}
