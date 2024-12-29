package com.app.androidcompose.ui.screens.main.gituser

import androidx.lifecycle.viewModelScope
import com.app.androidcompose.R
import com.app.androidcompose.support.util.DispatchersProvider
import com.app.androidcompose.ui.base.BaseViewModel
import com.app.androidcompose.ui.base.ErrorState
import com.app.androidcompose.ui.models.GitUserListUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import leegroup.module.domain.models.GitUserModel
import leegroup.module.domain.usecases.gituser.GetGitUserUseCase

@HiltViewModel
class GitUserListViewModel @Inject constructor(
    private val dispatchersProvider: DispatchersProvider,
    private val useCase: GetGitUserUseCase,
) : BaseViewModel() {

    private val _uiModel = MutableStateFlow(GitUserListUiModel())
    val uiModel = _uiModel.asStateFlow()

    fun handleAction(action: GitUserListAction) {
        when (action) {
            is GitUserListAction.LoadIfEmpty -> loadIfEmpty()
            is GitUserListAction.LoadMore -> loadMore()
        }
    }

    private fun loadIfEmpty() {
        if (isEmpty()) {
            _uiModel.update { oldValue ->
                oldValue.copy(showRefresh = false) // Hide retry button
            }
            loadMore()
        }
    }

    private fun loadMore() {
        if (isLoading()) return
        useCase(since = getSince(), perPage = PER_PAGE)
            .injectLoading()
            .onEach { result ->
                handleSuccess(result)
            }
            .flowOn(dispatchersProvider.io)
            .catch { e ->
                handleError(e)
            }
            .launchIn(viewModelScope)
    }

    override fun handleError(e: Throwable) {
        super.handleError(e)
        _uiModel.update { oldValue ->
            oldValue.copy(showRefresh = isEmpty())
        }
    }

    override fun onErrorConfirmation(errorState: ErrorState) {
        super.onErrorConfirmation(errorState)
        if (errorState is ErrorState.MessageError &&
            errorState.primaryRes == R.string.common_retry
        ) {
            loadIfEmpty()
        }
    }

    private fun handleSuccess(result: List<GitUserModel>) {
        _uiModel.update { oldValue ->
            val users = oldValue.users.plus(result).toImmutableList()
            oldValue.copy(users = users, showRefresh = isEmpty())
        }
    }

    private fun isEmpty() = _uiModel.value.users.isEmpty()

    private fun getSince() = _uiModel.value.users.size

    companion object {
        const val PER_PAGE = 20
    }
}
