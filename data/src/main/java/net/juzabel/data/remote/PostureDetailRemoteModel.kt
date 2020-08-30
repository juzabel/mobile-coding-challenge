package net.juzabel.data.remote

import com.squareup.moshi.Json

data class PostureDetailRemoteModel(
    @Json(name = "_id") val id: String,
    val name: String,
    val teacher: String,
    val duration: String,
    val picture: String,
    val description: String
)