package net.juzabel.domain.feature.posturelist

import net.juzabel.domain.core.Result
import net.juzabel.domain.core.UseCase
import net.juzabel.domain.feature.posturelist.model.Posture

class PostureListUseCase(private val postureListRepository: PostureListRepository) : UseCase<Unit, Result<List<Posture>>>{
    override suspend fun invoke(params: Unit): Result<List<Posture>> {
        return postureListRepository.getPostureList()
    }
}