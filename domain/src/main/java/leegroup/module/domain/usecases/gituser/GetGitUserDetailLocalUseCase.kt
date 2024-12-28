package leegroup.module.domain.usecases.gituser

import javax.inject.Inject
import leegroup.module.domain.repositories.GitUserDetailRepository

class GetGitUserDetailLocalUseCase @Inject constructor(
    private val repository: GitUserDetailRepository
) {

    operator fun invoke(login: String) = repository.getLocal(login)
}
