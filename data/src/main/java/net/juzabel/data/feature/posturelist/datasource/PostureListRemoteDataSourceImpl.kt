package net.juzabel.data.feature.posturelist.datasource

import net.juzabel.domain.core.Error
import net.juzabel.domain.core.Result
import net.juzabel.data.remote.Api
import net.juzabel.data.remote.mapToDomain
import net.juzabel.domain.feature.posturelist.model.Posture

class PostureListRemoteDataSourceImpl(private val api: Api) : PostureListRemoteDataSource {
    override suspend fun getPostureList(): Result<List<Posture>> {
       val response =  api.getPostureList()
        return if(response.isSuccessful){
            val mapRes = response.body()?.mapToDomain() ?: emptyList()
            Result.Ok(mapRes)
        }else {
            Result.Err(Error.GenericError("Something went wrong: Error ${response.code()}"))
        }
    }
}