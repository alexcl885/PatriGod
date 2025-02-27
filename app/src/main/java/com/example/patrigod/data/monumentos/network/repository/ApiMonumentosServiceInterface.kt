package com.example.patrigod.data.monumentos.network.repository

import com.example.patrigod.data.monumentos.network.models.response.ResponseMonumento
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiMonumentosServiceInterface {
    @GET("monumentos")
    suspend fun getAllMonumentos(@Header("Authorization") token: String) : Response<List<ResponseMonumento>>

}