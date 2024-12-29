package com.app.androidcompose.ui.screen.gituser

import app.cash.turbine.test
import com.app.androidcompose.MockUtil
import com.app.androidcompose.support.CoroutineTestRule
import com.app.androidcompose.ui.base.ErrorState
import com.app.androidcompose.ui.base.LoadingState
import com.app.androidcompose.ui.screens.main.gituser.GitUserListAction
import com.app.androidcompose.ui.screens.main.gituser.GitUserListViewModel
import com.app.androidcompose.ui.screens.main.gituser.GitUserListViewModel.Companion.PER_PAGE
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import leegroup.module.domain.models.GitUserModel
import leegroup.module.domain.usecases.gituser.GetGitUserUseCase
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GitUserListViewModelTest {

    @get:Rule
    val coroutinesRule = CoroutineTestRule()

    private val mockUseCase: GetGitUserUseCase = mockk()

    private lateinit var viewModel: GitUserListViewModel

    private val perPage = PER_PAGE
    private val gitUserModels = listOf(
        GitUserModel(
            id = 1,
            login = "longdt57",
            avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4",
            htmlUrl = "https://github.com/longdt57"
        ),
        GitUserModel(
            id = 2,
            login = "defunkt",
            avatarUrl = "https://avatars.githubusercontent.com/u/2?v=4",
            htmlUrl = "https://github.com/defunkt"
        ),
        GitUserModel(
            id = 3,
            login = "pjhyett",
            avatarUrl = "https://avatars.githubusercontent.com/u/3?v=4",
            htmlUrl = "https://github.com/pjhyett"
        )
    )

    @Before
    fun setUp() {
        every { mockUseCase(since = 0, perPage = perPage) } returns flowOf(gitUserModels)
        viewModel = GitUserListViewModel(
            coroutinesRule.testDispatcherProvider,
            mockUseCase
        )
    }

    @Test
    fun `When trigger load if empty & state is empty, it should load more`() = runTest {
        viewModel.handleAction(GitUserListAction.LoadIfEmpty)
        verify(exactly = 1) { mockUseCase(since = 0, perPage = perPage) }
    }

    @Test
    fun `When trigger load if empty & state is not empty, it should not load more`() = runTest {
        viewModel.handleAction(GitUserListAction.LoadMore)
        advanceUntilIdle()
        viewModel.handleAction(GitUserListAction.LoadIfEmpty)
        verify(exactly = 1) { mockUseCase(since = 0, perPage = perPage) }
    }

    @Test
    fun `When loading users successfully, it shows the user list`() = runTest {
        viewModel.handleAction(GitUserListAction.LoadMore)
        viewModel.uiModel.test {
            val updated = expectMostRecentItem()
            assertEquals("Expected updated users to match gitUserModels", gitUserModels, updated.users)
        }
        verify(exactly = 1) { mockUseCase(since = 0, perPage = perPage) }
    }

    @Test
    fun `When loading users failed, it shows the corresponding error`() = runTest {
        val error = RuntimeException("Network error")
        every { mockUseCase(since = 0, perPage = perPage) } returns flow { throw error }

        viewModel.handleAction(GitUserListAction.LoadMore)
        viewModel.error.test {
            val errorState = expectMostRecentItem()
            assertEquals("Expected error state to be UnknownError", ErrorState.Common, errorState)
        }
    }

    @Test
    fun `When loading users, it shows and hides loading correctly`() = runTest {
        every { mockUseCase(since = 0, perPage = perPage) } returns flow {
            delay(1000)
            emit(gitUserModels)
        }
        viewModel.handleAction(GitUserListAction.LoadMore)
        viewModel.loading.test {
            val loading = expectMostRecentItem()
            assertEquals("Expected loading state to be Loading", LoadingState.Loading(), loading)
        }
    }

    @Test
    fun `When loading users, it can't call load more`() = runTest {
        every { mockUseCase(since = 0, perPage = perPage) } returns flow {
            delay(1000)
            emit(gitUserModels)
        }
        viewModel.handleAction(GitUserListAction.LoadMore)
        viewModel.handleAction(GitUserListAction.LoadMore)
        verify(exactly = 1) { mockUseCase(since = 0, perPage = perPage) }
    }

    @Test
    fun `When api onErrorConfirmation is called, it calls load if empty again`() = runTest {
        viewModel.onErrorConfirmation(MockUtil.apiErrorState)
        verify(exactly = 1) { mockUseCase(since = 0, perPage = perPage) }
    }

    @Test
    fun `When network onErrorConfirmation is called, it calls load if empty again`() = runTest {
        viewModel.onErrorConfirmation(ErrorState.Network())
        verify(exactly = 1) { mockUseCase(since = 0, perPage = perPage) }
    }

    @Test
    fun `When common onErrorConfirmation is called, it doesn't calls load if empty again`() = runTest {
        viewModel.onErrorConfirmation(ErrorState.Common)
        verify(exactly = 0) { mockUseCase(since = 0, perPage = perPage) }
    }
}
