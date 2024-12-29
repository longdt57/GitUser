package leegroup.module.domain.usecases.gituser

import javax.inject.Inject
import kotlinx.coroutines.flow.flow
import leegroup.module.domain.repositories.GitUserDetailRepository

class GetGitUserDetailLocalUseCase @Inject constructor(
    private val repository: GitUserDetailRepository
) {

    operator fun invoke(login: String) = flow {
        emit(repository.getLocal(login))
    }
}
