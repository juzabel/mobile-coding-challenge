package net.juzabel.data.local

import net.juzabel.domain.feature.posturelist.model.Posture

fun PostureLocalModel.mapToDomain() = Posture(id, name, picture, description)
fun List<PostureLocalModel>.mapToDomain() = map { it.mapToDomain() }

fun Posture.mapToLocal() = PostureLocalModel(id, name, picture, description)
fun List<Posture>.mapToLocal() = map { it.mapToLocal() }