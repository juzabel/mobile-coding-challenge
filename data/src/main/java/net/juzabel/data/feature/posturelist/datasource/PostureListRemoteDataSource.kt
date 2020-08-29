package net.juzabel.data.feature.posturelist.datasource

import net.juzabel.domain.core.Result
import net.juzabel.domain.feature.posturelist.model.Posture

interface PostureListRemoteDataSource {
    suspend fun getPostureList() : Result<List<Posture>>
}