package net.juzabel.domain.feature.posturedetail

import net.juzabel.domain.feature.posturedetail.model.PostureDetail
import net.juzabel.domain.core.Result

interface PostureDetailRepository {
    suspend fun getPostureDetailList(): Result<List<PostureDetail>>
    suspend fun getPostureDetail(id: String): Result<PostureDetail>
}