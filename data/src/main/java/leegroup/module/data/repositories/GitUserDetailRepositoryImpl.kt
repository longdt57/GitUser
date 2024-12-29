package leegroup.module.data.repositories

import javax.inject.Inject
import leegroup.module.data.extensions.transform
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

    override suspend fun getRemote(login: String) = transform {
        appService.getGitUserDetail(login).let { userDetail ->
            saveToLocal(userDetail)
            mapToDomain(userDetail)
        }
    }

    override suspend fun getLocal(login: String): GitUserDetailModel? {
        return userDao.getUserDetail(login)?.let { mapToDomain(it) }
    }

    private suspend fun saveToLocal(user: GitUserDetail) {
        userDao.upsert(user)
    }

    private fun mapToDomain(user: GitUserDetail) = user.mapToDomain()
}