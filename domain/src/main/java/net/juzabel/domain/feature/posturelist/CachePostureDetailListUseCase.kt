package net.juzabel.domain.feature.posturelist

import net.juzabel.domain.core.Result
import net.juzabel.domain.core.UseCase
import net.juzabel.domain.feature.posturedetail.PostureDetailRepository
import net.juzabel.domain.feature.posturedetail.model.PostureDetail


class CachePostureDetailListUseCase(private val postureDetailRepository: PostureDetailRepository) :
    UseCase<Unit, Result<List<PostureDetail>>> {
    override suspend fun invoke(params: Unit): Result<List<PostureDetail>> {
        return postureDetailRepository.getPostureDetailList()
    }
}