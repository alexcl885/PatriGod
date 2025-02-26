package com.example.patrigod.data.user.network.service

import android.util.Log
import com.example.patrigod.data.user.models.Request.RequestUsuario
import com.example.patrigod.data.user.models.Request.RequestUsuarioEntero
import com.example.patrigod.data.user.models.Response.ResponseUsuario
import com.example.patrigod.data.user.models.Response.ResponseUsuarioEntero
import com.example.patrigod.data.user.network.repository.ApiUsuarioServiceInterface
import com.google.gson.Gson
import javax.inject.Inject

class ApiServiceUsuario @Inject constructor(
    private val apiUsuarioService: ApiUsuarioServiceInterface
) {
    suspend fun loginService(usuario: RequestUsuario): Result<ResponseUsuario> {
        return try {
            val response = apiUsuarioService.loginUser(usuario)

            if (response.isSuccessful) {
                response.body()?.let { body ->
                    Result.success(body)
                } ?: Result.failure(RuntimeException("Respuesta de usuario nulo en el logueo"))
            } else {
                val errorMsg = response.errorBody()?.string() ?: "Error no reconocido al loguear"
                Result.failure(RuntimeException(errorMsg))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    suspend fun registerService(user: RequestUsuarioEntero): Result<ResponseUsuarioEntero> {
        try {
            Log.d("RegisterService", "Enviando datos: ${Gson().toJson(user)}")

            val response = apiUsuarioService.registerUser(user)

            Log.d("RegisterService", "Código de respuesta: ${response.code()}") // <-- Ver código HTTP

            if (response.isSuccessful) {
                response.body()?.let { body ->
                    Log.d("RegisterService", "Registro exitoso: $body")
                    return Result.success(body)
                } ?: return Result.failure(RuntimeException("Respuesta de usuario nulo en el registro"))
            } else {
                val errorMsg = response.errorBody()?.string() ?: "Error no reconocido al registrar"

                Log.e("RegisterService", "Error en la API: Código ${response.code()}, Mensaje: $errorMsg") // <-- Ver mensaje de error

                return Result.failure(RuntimeException("Error ${response.code()}: $errorMsg")) // <-- Devuelve error detallado
            }
        } catch (e: Exception) {
            Log.e("RegisterService", "Excepción en el registro", e)
            return Result.failure(e)
        }
    }


}
