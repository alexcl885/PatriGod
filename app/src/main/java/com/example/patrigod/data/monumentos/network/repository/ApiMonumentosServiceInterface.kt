package com.example.patrigod.data.monumentos.network.repository

import com.example.patrigod.data.monumentos.network.models.request.RequestMonumento
import com.example.patrigod.data.monumentos.network.models.response.ResponseMonumento
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiMonumentosServiceInterface {
    @GET("monumentos")
    suspend fun getAllMonumentos(@Header("Authorization") token: String): Response<List<ResponseMonumento>>

    @POST("monumentos")
    suspend fun postMonumento(
        @Header("Authorization") token: String,
        @Body monumento: RequestMonumento
    ): Response<ResponseMonumento>

    @PATCH("monumentos/{idMonu}")
    suspend fun patchMonumento(
        @Header("Authorization") token: String,
        @Path("idMonu") monumentoId: String,
        @Body monumento: RequestMonumento
    ): Response<ResponseMonumento>

    @DELETE("monumentos/{idMonu}")
    suspend fun deleteMonumento(
        @Header("Authorization") token: String,
        @Path("idMonu") monumentoId: String
    ): Response<Unit>
}