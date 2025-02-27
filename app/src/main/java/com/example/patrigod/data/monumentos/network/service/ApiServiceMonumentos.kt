package com.example.patrigod.data.monumentos.network.service

import com.example.patrigod.data.monumentos.network.models.request.RequestMonumento
import com.example.patrigod.data.monumentos.network.models.response.ResponseMonumento
import com.example.patrigod.data.monumentos.network.repository.ApiMonumentosServiceInterface
import javax.inject.Inject

class ApiServiceMonumentos @Inject constructor(
    private val apiMonumentosService: ApiMonumentosServiceInterface
) {
    suspend fun getAllMonumentos(token: String): Result<List<ResponseMonumento>> {
        return try {
            val response = apiMonumentosService.getAllMonumentos("Bearer $token")
            if (response.isSuccessful) {
                Result.success(response.body() ?: emptyList())
            } else {
                Result.failure(RuntimeException(response.errorBody()?.string() ?: "Error desconocido"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun postMonumentoService(token: String, requestMonumento: RequestMonumento): Result<ResponseMonumento> {
        return try {
            val response = apiMonumentosService.postMonumento("Bearer $token", requestMonumento)
            if (response.isSuccessful) {
                response.body()?.let { Result.success(it) } ?: Result.failure(RuntimeException("Cuerpo de respuesta nulo"))
            } else {
                Result.failure(RuntimeException(response.errorBody()?.string() ?: "Error al crear monumento"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun deleteMonumentoService(token: String, monumentoId: String): Result<Unit> {
        return try {
            val response = apiMonumentosService.deleteMonumento("Bearer $token", monumentoId)
            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(RuntimeException(response.errorBody()?.string() ?: "Error al eliminar monumento"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    suspend fun patchMonumentoService(token: String, monumentoId: String, requestMonumento: RequestMonumento): Result<ResponseMonumento> {
        return try {
            val response = apiMonumentosService.patchMonumento("Bearer $token", monumentoId, requestMonumento)
            if (response.isSuccessful) {
                response.body()?.let { Result.success(it) } ?: Result.failure(RuntimeException("Cuerpo de respuesta nulo"))
            } else {
                Result.failure(RuntimeException(response.errorBody()?.string() ?: "Error al actualizar monumento"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}