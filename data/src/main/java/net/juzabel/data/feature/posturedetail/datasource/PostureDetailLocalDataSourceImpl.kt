package net.juzabel.data.feature.posturedetail.datasource

import net.juzabel.data.local.PostureDetailDao
import net.juzabel.data.local.mapToDomain
import net.juzabel.data.local.mapToLocal
import net.juzabel.domain.feature.posturedetail.model.PostureDetail

class PostureDetailLocalDataSourceImpl(private val postureDetailDao: PostureDetailDao) :
    PostureDetailLocalDataSource {
    override suspend fun savePostureDetails(postureDetails: List<PostureDetail>) {
        return postureDetailDao.insertAll(postureDetails.mapToLocal())
    }

    override suspend fun getPostureDetail(id: String): PostureDetail {
        return postureDetailDao.getPostureDetail(id).mapToDomain()
    }
}