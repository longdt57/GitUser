package leegroup.module.data.repositories

import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import leegroup.module.data.MockUtil
import leegroup.module.data.local.room.GitUserDetailDao
import leegroup.module.data.remote.services.ApiService
import leegroup.module.domain.repositories.GitUserDetailRepository
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GitUserDetailRepositoryTest {

    private lateinit var mockService: ApiService
    private lateinit var mockUserDao: GitUserDetailDao
    private lateinit var repository: GitUserDetailRepository

    private val login = MockUtil.login
    private val gitUserDetail = MockUtil.gitUserDetail
    private val gitUserDetailModel = MockUtil.gitUserDetailModel

    @Before
    fun setUp() {
        mockService = mockk()
        mockUserDao = mockk()
        repository = GitUserDetailRepositoryImpl(mockService, mockUserDao)
    }

    @Test
    fun `When request successful, it returns success`() = runTest {
        val expected = gitUserDetail
        coEvery { mockService.getGitUserDetail(login) } returns expected
        coEvery { mockUserDao.upsert(expected) } returns Unit

        repository.getRemote(login).collect {
            it shouldBe gitUserDetailModel
            coVerify(exactly = 1) { mockUserDao.upsert(expected) }
        }
    }

    @Test
    fun `When request failed, it returns error`() = runTest {
        val expected = RuntimeException()
        coEvery { mockService.getGitUserDetail(login) } throws expected

        repository.getRemote(login).catch {
            it shouldBe expected
        }.collect()
    }

    @Test
    fun `getLocal should return transformed local data`() = runTest {
        val expected = gitUserDetail
        coEvery { mockUserDao.getUserDetail(login) } returns flowOf(expected)

        repository.getLocal(login).collect { result ->
            result shouldBe gitUserDetailModel
        }
    }

    @Test
    fun `getLocal should return nothing when database is empty`() = runTest {
        coEvery { mockUserDao.getUserDetail(login) } returns flowOf()

        repository.getLocal(login).collect { result ->
            assert(false) // won't reach this
        }
    }
}
