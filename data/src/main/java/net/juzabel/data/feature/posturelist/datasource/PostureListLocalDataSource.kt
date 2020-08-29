package net.juzabel.data.feature.posturelist.datasource

import net.juzabel.domain.feature.posturelist.model.Posture
import net.juzabel.domain.core.Result

interface PostureListLocalDataSource {
    suspend fun getPostureList(): List<Posture>

    suspend fun savePostureList(postureList: List<Posture>)
}