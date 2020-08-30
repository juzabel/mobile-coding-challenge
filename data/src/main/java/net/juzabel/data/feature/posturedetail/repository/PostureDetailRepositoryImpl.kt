package net.juzabel.data.feature.posturedetail.repository

import net.juzabel.data.feature.posturedetail.datasource.PostureDetailLocalDataSource
import net.juzabel.data.feature.posturedetail.datasource.PostureDetailRemoteDataSource
import net.juzabel.domain.core.Error
import net.juzabel.domain.core.Result
import net.juzabel.domain.feature.posturedetail.PostureDetailRepository
import net.juzabel.domain.feature.posturedetail.model.PostureDetail

class PostureDetailRepositoryImpl(
    private val postureDetailLocalDataSource: PostureDetailLocalDataSource,
    private val postureDetailRemoteDataSource: PostureDetailRemoteDataSource) : PostureDetailRepository {

    override suspend fun getPostureDetailList(): Result<List<PostureDetail>> {
        val result = postureDetailRemoteDataSource.getPostureDetailList()
        if(result is Result.Ok){
            postureDetailLocalDataSource.savePostureDetails(result.value)
        }
        return result
    }

    override suspend fun getPostureDetail(id: String): Result<PostureDetail> {
        return try {
            Result.Ok(postureDetailLocalDataSource.getPostureDetail(id))
        }catch (e: Exception){
            Result.Err(Error.GenericError(e.message ?: ""))
        }
    }

}