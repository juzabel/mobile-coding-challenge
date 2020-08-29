package net.juzabel.data.feature.posturelist.repository

import net.juzabel.data.feature.posturelist.datasource.PostureListLocalDataSource
import net.juzabel.data.feature.posturelist.datasource.PostureListRemoteDataSource
import net.juzabel.domain.core.Error
import net.juzabel.domain.core.Result
import net.juzabel.domain.feature.posturelist.PostureListRepository
import net.juzabel.domain.feature.posturelist.model.Posture
import java.lang.Exception

class PostureListRepositoryImpl(
    private val postureListLocalDataSource: PostureListLocalDataSource,
    private val postureListRemoteDataSource: PostureListRemoteDataSource
) : PostureListRepository {
    override suspend fun getPostureList(): Result<List<Posture>> {
        val localValues = postureListLocalDataSource.getPostureList()
        return try {
            if (localValues.isNotEmpty()) {
                Result.Ok(localValues)
            } else {
                val remoteValues = postureListRemoteDataSource.getPostureList()
                if (remoteValues is Result.Ok) {
                    postureListLocalDataSource.savePostureList(remoteValues.value)
                }
                remoteValues
            }
        } catch (e: Exception){
            Result.Err(Error.GenericError(e.message ?: ""))
        }
    }
}