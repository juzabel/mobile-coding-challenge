package net.juzabel.data

import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import net.juzabel.data.feature.posturelist.datasource.PostureListLocalDataSource
import net.juzabel.data.feature.posturelist.datasource.PostureListRemoteDataSource
import net.juzabel.data.feature.posturelist.repository.PostureListRepositoryImpl
import net.juzabel.domain.core.Error
import net.juzabel.domain.feature.posturelist.PostureListRepository
import net.juzabel.domain.feature.posturelist.model.Posture
import net.juzabel.domain.core.Result
import org.junit.Assert
import org.junit.Before
import org.junit.Test


@ExperimentalCoroutinesApi
class PostureListRepositoryUnitTest {

    private lateinit var repository: PostureListRepository
    @MockK
    lateinit var postureListLocalDataSource: PostureListLocalDataSource
    @MockK
    lateinit var postureListRemoteDataSource: PostureListRemoteDataSource

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        repository =
            PostureListRepositoryImpl(postureListLocalDataSource, postureListRemoteDataSource)
    }

    @Test
    fun testNoLocalOK() = runBlockingTest {
        val retList = listOf(
            Posture("1", "name", "pic", "description")
        )
        coEvery { postureListLocalDataSource.getPostureList() } returns emptyList()
        coEvery { postureListRemoteDataSource.getPostureList() } returns Result.Ok(retList)
        coEvery { postureListLocalDataSource.savePostureList(any()) } returns Unit

        val resultList = repository.getPostureList()

        Assert.assertTrue(resultList is Result.Ok)
        Assert.assertTrue((resultList as Result.Ok).value == retList)
        coVerify(exactly = 1) { postureListLocalDataSource.savePostureList(retList) }
    }

    @Test
    fun testLocalOK() = runBlockingTest {
        val retList = listOf(
            Posture("1", "name1", "pic1", "description1"),
            Posture("2", "name2", "pic2", "description2")
        )
        coEvery { postureListLocalDataSource.getPostureList() } returns retList

        val resultList = repository.getPostureList()

        Assert.assertTrue(resultList is Result.Ok)
        Assert.assertTrue((resultList as Result.Ok).value == retList)
        coVerify(exactly = 0) { postureListLocalDataSource.savePostureList(retList) }
        coVerify(exactly = 0) { postureListRemoteDataSource.getPostureList() }
        coVerify(exactly = 1) { postureListLocalDataSource.getPostureList() }
    }

    @Test
    fun testNoLocalKO() = runBlockingTest {
        val ret = Result.Err<List<Posture>>(Error.GenericError("test error"))

        coEvery { postureListLocalDataSource.getPostureList() } returns emptyList()
        coEvery { postureListRemoteDataSource.getPostureList() } returns ret

        val resultList = repository.getPostureList()
        Assert.assertTrue(resultList is Result.Err)
        Assert.assertTrue((resultList as Result.Err).err == ret.err)
        coVerify(exactly = 0) { postureListLocalDataSource.savePostureList(any()) }
        coVerify(exactly = 1) { postureListLocalDataSource.getPostureList() }
        coVerify(exactly = 1) { postureListRemoteDataSource.getPostureList() }
    }

    @Test
    fun testLocalKO() = runBlockingTest {
        coEvery { postureListLocalDataSource.getPostureList() } throws Exception()

        val resultList = repository.getPostureList()

        Assert.assertTrue(resultList is Result.Err)
    }
}