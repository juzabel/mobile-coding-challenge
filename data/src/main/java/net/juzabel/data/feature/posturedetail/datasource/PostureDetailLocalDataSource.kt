package net.juzabel.data.feature.posturedetail.datasource

import net.juzabel.domain.feature.posturedetail.model.PostureDetail

interface PostureDetailLocalDataSource {
    suspend fun savePostureDetails(postureDetails : List<PostureDetail>)
    suspend fun getPostureDetail(id: String) : PostureDetail
}