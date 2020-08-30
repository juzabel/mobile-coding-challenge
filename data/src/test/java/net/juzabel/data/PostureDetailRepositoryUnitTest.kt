package net.juzabel.data

import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import net.juzabel.data.feature.posturedetail.datasource.PostureDetailLocalDataSource
import net.juzabel.data.feature.posturedetail.datasource.PostureDetailRemoteDataSource
import net.juzabel.data.feature.posturedetail.repository.PostureDetailRepositoryImpl
import net.juzabel.domain.core.Error
import net.juzabel.domain.core.Result
import net.juzabel.domain.feature.posturedetail.PostureDetailRepository
import net.juzabel.domain.feature.posturedetail.model.PostureDetail
import net.juzabel.domain.feature.posturelist.model.Posture
import org.junit.Assert
import org.junit.Before
import org.junit.Test


@ExperimentalCoroutinesApi
class PostureDetailRepositoryUnitTest {

    private lateinit var repository: PostureDetailRepository

    @MockK
    lateinit var postureDetailLocalDataSource: PostureDetailLocalDataSource

    @MockK
    lateinit var postureDetailRemoteDataSource: PostureDetailRemoteDataSource

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        repository =
            PostureDetailRepositoryImpl(postureDetailLocalDataSource, postureDetailRemoteDataSource)
    }

    @Test
    fun downloadAllOk() = runBlockingTest {
        val retList = listOf(
            PostureDetail("1", "name", "teacher", "duration", "pic", "description")
        )

        coEvery { postureDetailRemoteDataSource.getPostureDetailList() } returns Result.Ok(retList)
        coEvery { postureDetailLocalDataSource.savePostureDetails(any()) } returns Unit

        val result = repository.getPostureDetailList()

        Assert.assertTrue(result is Result.Ok)
        Assert.assertTrue((result as Result.Ok).value == retList)

        coVerify(exactly = 1) { postureDetailLocalDataSource.savePostureDetails(retList) }

    }

    @Test
    fun downloadAllKO() = runBlockingTest {
        val ret = Result.Err<List<PostureDetail>>(Error.GenericError("Test error"))
        coEvery { postureDetailRemoteDataSource.getPostureDetailList() } returns ret

        val result = repository.getPostureDetailList()

        Assert.assertTrue(result is Result.Err)
        Assert.assertTrue((result as Result.Err).err == ret.err)

        coVerify(exactly = 0) { postureDetailLocalDataSource.savePostureDetails(any()) }
    }

    @Test
    fun getDetail() = runBlockingTest {
        val ret = PostureDetail("idok", "name", "teacher", "duration", "picture", "description")

        val idOk = "idok"
        val idKo = "idko"

        coEvery { postureDetailLocalDataSource.getPostureDetail(idOk) } returns ret
        coEvery { postureDetailLocalDataSource.getPostureDetail(idKo) } throws Exception()

        val retValue = repository.getPostureDetail(idOk)
        Assert.assertTrue(retValue is Result.Ok)
        Assert.assertTrue((retValue as Result.Ok).value == ret)

        val retValueKo = repository.getPostureDetail(idKo)
        Assert.assertTrue(retValueKo is Result.Err)
    }
}