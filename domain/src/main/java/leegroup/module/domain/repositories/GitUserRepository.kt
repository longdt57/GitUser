package leegroup.module.domain.repositories

import leegroup.module.domain.models.GitUserModel

interface GitUserRepository {
    suspend fun getRemote(since: Int, perPage: Int): List<GitUserModel>
    suspend fun getLocal(since: Int, perPage: Int): List<GitUserModel>
}