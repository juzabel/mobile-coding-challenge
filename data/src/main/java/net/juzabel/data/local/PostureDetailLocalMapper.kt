package net.juzabel.data.local

import net.juzabel.domain.feature.posturedetail.model.PostureDetail


fun PostureDetailLocalModel.mapToDomain() =
    PostureDetail(id, name, teacher, duration, picture, description)

fun PostureDetail.mapToLocal() =
    PostureDetailLocalModel(id, name, teacher, duration, picture, description)

fun List<PostureDetail>.mapToLocal() = map { it.mapToLocal() }