package net.juzabel.data.remote

import net.juzabel.domain.feature.posturedetail.model.PostureDetail

fun PostureDetailRemoteModel.mapToDomain() = PostureDetail(id, name, teacher, duration, picture, description)

fun List<PostureDetailRemoteModel>.mapToDomain() = map { it.mapToDomain() }