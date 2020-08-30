package net.juzabel.data.feature.posturelist.datasource

import net.juzabel.data.local.PostureDao
import net.juzabel.data.local.PostureDatabase
import net.juzabel.data.local.mapToDomain
import net.juzabel.data.local.mapToLocal
import net.juzabel.domain.feature.posturelist.model.Posture

class PostureListLocalDataSourceImpl(private val postureDao: PostureDao) :
    PostureListLocalDataSource {
    override suspend fun getPostureList(): List<Posture> =
        postureDao.getAll().mapToDomain()

    override suspend fun savePostureList(postureList: List<Posture>) {
        postureDao.insertAll(postureList.mapToLocal())
    }
}