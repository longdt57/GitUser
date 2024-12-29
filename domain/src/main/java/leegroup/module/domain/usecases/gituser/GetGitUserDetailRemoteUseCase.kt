package leegroup.module.domain.usecases.gituser

import javax.inject.Inject
import kotlinx.coroutines.flow.flow
import leegroup.module.domain.repositories.GitUserDetailRepository

class GetGitUserDetailRemoteUseCase @Inject constructor(
    private val repository: GitUserDetailRepository
) {

    operator fun invoke(login: String) = flow {
        emit(repository.getRemote(login))
    }
}
