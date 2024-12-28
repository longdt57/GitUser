package leegroup.module.data.repositories

import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import leegroup.module.data.extensions.flowTransform
import leegroup.module.data.local.room.GitUserDetailDao
import leegroup.module.data.models.GitUserDetail
import leegroup.module.data.models.mapToDomain
import leegroup.module.data.remote.services.ApiService
import leegroup.module.domain.models.GitUserDetailModel
import leegroup.module.domain.repositories.GitUserDetailRepository

class GitUserDetailRepositoryImpl @Inject constructor(
    private val appService: ApiService,
    private val userDao: GitUserDetailDao,
) : GitUserDetailRepository {

    override fun getRemote(login: String): Flow<GitUserDetailModel> = flowTransform {
        appService.getGitUserDetail(login).let { userDetail ->
            saveToLocal(userDetail)
            mapToDomain(userDetail)
        }
    }

    override fun getLocal(login: String): Flow<GitUserDetailModel> {
        return userDao.getUserDetail(login).map { user -> mapToDomain(user) }
    }

    private suspend fun saveToLocal(user: GitUserDetail) {
        userDao.upsert(user)
    }

    private fun mapToDomain(user: GitUserDetail) = user.mapToDomain()
}