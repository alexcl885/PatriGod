package com.example.patrigod.data.monumentos.network.service

import com.example.patrigod.data.monumentos.network.models.response.ResponseMonumento
import com.example.patrigod.data.user.network.repository.ApiUsuarioServiceInterface
import javax.inject.Inject

class ApiServiceMonumentos @Inject constructor(
    private val apiMonumentosService: ApiUsuarioServiceInterface
) {
    /*suspend fun getAllMonumentos (token: String): Result<List<ResponseMonumento>> {
        try{
            val t = "Bearer "+token
            val response = apiMonumentosService.getAllMonumentos(t)
            if (response.isSuccessful){
                response.body()?.let{
                        body -> return Result.success(body)
                }?: return Result.failure(RuntimeException("body nulo"))

            }else{
                //Me puede mandar un error personalizado, pero la petición es correcta.
                //ó que la API me haya mandado un mensaje de error por alguna excepción. Petición no correcta.
                val errorMsg = response.errorBody()?.string() ?: "Error no reconocido al listar"   //error personalizado o Excepción
                return Result.failure(RuntimeException(errorMsg))
            }

        }catch (e: Exception){
            return Result.failure(e)
        }
    }*/

}