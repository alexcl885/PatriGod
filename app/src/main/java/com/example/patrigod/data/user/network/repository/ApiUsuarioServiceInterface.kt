package com.example.patrigod.data.user.network.repository

import com.example.patrigod.data.user.models.Request.RequestUsuario
import com.example.patrigod.data.user.models.Request.RequestUsuarioEntero
import com.example.patrigod.data.user.models.Response.ResponseUsuario
import com.example.patrigod.data.user.models.Response.ResponseUsuarioEntero
import retrofit2.http.Body
import retrofit2.http.POST
/**
 * Todas las rutas de mi backend relacionado
 * con el login y el registro del usuario
 * */
interface ApiUsuarioServiceInterface {
    @POST("auth")
    suspend fun loginUser(@Body usuario: RequestUsuario): retrofit2.Response<ResponseUsuario>

    @POST("register")
    suspend fun registerUser(@Body usuario: RequestUsuarioEntero): retrofit2.Response<ResponseUsuarioEntero>
}