package net.juzabel.data.remote

import retrofit2.Response
import retrofit2.http.GET

interface Api {
    @GET("postures")
    suspend fun getPostureList() : Response<List<PostureRemoteModel>>

    @GET("posture_detail")
    suspend fun getPostureDetail() : Response<PostureRemoteModel>
}