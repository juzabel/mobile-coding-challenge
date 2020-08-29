package net.juzabel.data.feature.posturelist.datasource

import net.juzabel.data.local.PostureDatabase
import net.juzabel.data.local.mapToDomain
import net.juzabel.data.local.mapToLocal
import net.juzabel.domain.feature.posturelist.model.Posture

class PostureListLocalDataSourceImpl(private val postureDatabase: PostureDatabase) :
    PostureListLocalDataSource {
    override suspend fun getPostureList(): List<Posture> =
        postureDatabase.postureDao().getAll().mapToDomain()

    override suspend fun savePostureList(postureList: List<Posture>) {
        postureDatabase.postureDao().insertAll(postureList.mapToLocal())
    }
}