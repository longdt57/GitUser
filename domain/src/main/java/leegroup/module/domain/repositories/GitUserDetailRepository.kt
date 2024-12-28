package leegroup.module.domain.repositories

import kotlinx.coroutines.flow.Flow
import leegroup.module.domain.models.GitUserDetailModel

interface GitUserDetailRepository {
    fun getRemote(login: String): Flow<GitUserDetailModel>
    fun getLocal(login: String): Flow<GitUserDetailModel>
}