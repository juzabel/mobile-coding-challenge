package net.juzabel.data.remote

import net.juzabel.domain.feature.posturelist.model.Posture

fun PostureRemoteModel.mapToDomain() = Posture(id, name, picture, description)
fun List<PostureRemoteModel>.mapToDomain() = map { it.mapToDomain() }

fun Posture.mapToRemote() = PostureRemoteModel(id, name, picture, description)
fun List<Posture>.mapToRemote() = map { it.mapToRemote() }