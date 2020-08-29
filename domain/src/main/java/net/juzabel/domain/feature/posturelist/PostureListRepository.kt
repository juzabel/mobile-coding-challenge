package net.juzabel.domain.feature.posturelist

import net.juzabel.domain.feature.posturelist.model.Posture
import net.juzabel.domain.core.Result


interface PostureListRepository {
    suspend fun getPostureList() : Result<List<Posture>>
}