package net.juzabel.data.feature.posturedetail.datasource

import net.juzabel.domain.core.Result
import net.juzabel.domain.feature.posturedetail.model.PostureDetail

interface PostureDetailRemoteDataSource {
    suspend fun getPostureDetailList(): Result<List<PostureDetail>>
}