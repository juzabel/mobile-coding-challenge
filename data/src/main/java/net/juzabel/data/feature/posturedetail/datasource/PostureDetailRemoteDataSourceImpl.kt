package net.juzabel.data.feature.posturedetail.datasource

import net.juzabel.data.remote.Api
import net.juzabel.data.remote.mapToDomain
import net.juzabel.domain.core.Error
import net.juzabel.domain.core.Result
import net.juzabel.domain.feature.posturedetail.model.PostureDetail

class PostureDetailRemoteDataSourceImpl (val api: Api) : PostureDetailRemoteDataSource {
    override suspend fun getPostureDetailList(): Result<List<PostureDetail>> {
        val response =  api.getPostureDetail()
        return if(response.isSuccessful){
            val result = response.body()?.mapToDomain() ?: emptyList()
            Result.Ok(result)
        }else{
            Result.Err(Error.GenericError("Something went wrong: Error ${response.code()}"))
        }
    }
}