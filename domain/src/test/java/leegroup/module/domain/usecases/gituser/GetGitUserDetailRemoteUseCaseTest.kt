package leegroup.module.domain.usecases.gituser

import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import leegroup.module.domain.MockUtil
import leegroup.module.domain.repositories.GitUserDetailRepository
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetGitUserDetailRemoteUseCaseTest {

    private lateinit var mockRepository: GitUserDetailRepository
    private lateinit var useCase: GetGitUserDetailRemoteUseCase

    private val login = MockUtil.login
    private val gitUserDetail = MockUtil.gitUserDetail

    @Before
    fun setUp() {
        mockRepository = mockk()
        useCase = GetGitUserDetailRemoteUseCase(mockRepository)
    }

    @Test
    fun `When request successful, it returns success`() = runTest {
        val expected = gitUserDetail
        every { mockRepository.getRemote(login) } returns flowOf(expected)

        useCase(login).collect {
            it shouldBe expected
        }
    }

    @Test
    fun `When request failed, it returns error`() = runTest {
        val expected = MockUtil.apiException
        every { mockRepository.getRemote(login) } returns flow { throw expected }

        useCase(login).catch {
            it shouldBe expected
        }.collect()
    }
}