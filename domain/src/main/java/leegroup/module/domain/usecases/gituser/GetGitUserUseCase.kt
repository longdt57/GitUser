package leegroup.module.domain.usecases.gituser

import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import leegroup.module.domain.models.GitUserModel
import leegroup.module.domain.repositories.GitUserRepository

class GetGitUserUseCase @Inject constructor(
    private val repository: GitUserRepository
) {

    operator fun invoke(since: Int, perPage: Int): Flow<List<GitUserModel>> {
        return flow {
            val localData = repository.getLocal(since, perPage)
            if (localData.isEmpty()) {
                emit(repository.getRemote(since, perPage))
            } else {
                emit(localData)
            }
        }
    }
}