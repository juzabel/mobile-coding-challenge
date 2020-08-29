package net.juzabel.domain.feature.posturedetail

import net.juzabel.domain.core.Result
import net.juzabel.domain.core.UseCase
import net.juzabel.domain.feature.posturedetail.model.PostureDetail

class GetPostureDetailUseCase(private val postureDetailRepository: PostureDetailRepository) : UseCase<String, Result<PostureDetail>> {
    override suspend fun invoke(params: String): Result<PostureDetail> {
        return postureDetailRepository.getPostureDetail(params)
    }
}