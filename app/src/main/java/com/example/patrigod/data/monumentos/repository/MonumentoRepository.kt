package com.example.patrigod.data.monumentos.repository

import android.content.Context.MODE_PRIVATE
import android.util.Log
import com.example.patrigod.data.monumentos.network.service.ApiServiceMonumentos
import com.example.patrigod.data.monumentos.objects_models.RepositoryObjects
import com.example.patrigod.domain.monumentos.interfaces.InterfaceDao
import com.example.patrigod.domain.monumentos.models.ListMonumentos
import com.example.patrigod.domain.monumentos.models.Monumento
import com.example.patrigod.domain.usuarios.models.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MonumentoRepository @Inject constructor(
    val apiServiceMonumento : ApiServiceMonumentos
): InterfaceDao {
    override suspend fun getNativeMonumentos(): List<Monumento> {
        return try {
            val response = apiServiceMonumento.getAllMonumentos(User.token.toString())

                Log.e("API_FAILURE", "La petición falló con error: ${response.exceptionOrNull()}")
                Log.e("TOKEN VER", "El token es : ${User.token}")

            response.getOrNull()?.map { responseMonumento ->
                Monumento(
                    id = responseMonumento.idMonu.hashCode(), // Si necesitas un Int, puedes usar esto
                    idMonu = responseMonumento.idMonu,
                    nombre = responseMonumento.nombre,
                    ciudad = responseMonumento.ciudad,
                    fecha = responseMonumento.fecha,
                    descripcion = responseMonumento.descripcion,
                    imagen = responseMonumento.imagen,
                    descripcionPlus = responseMonumento.descripcionPlus
                )
            } ?: emptyList()
        } catch (e: Exception) {
            Log.e("ERROR LISTAR MONU", "Lista vacia de monumentos")
            emptyList()
        }
    }



    suspend fun getMonumentos() : List<Monumento> {
        return try {
            val response = apiServiceMonumento.getAllMonumentos(User.token.toString())

                Log.e("API_FAILURE", "La petición falló con error: ${response.exceptionOrNull()}")

            response.getOrNull()?.map { responseMonumento ->
                Monumento(
                    id = responseMonumento.idMonu.toIntOrNull() ?: 0,
                    idMonu = responseMonumento.idMonu,
                    nombre = responseMonumento.nombre,
                    ciudad = responseMonumento.ciudad,
                    fecha = responseMonumento.fecha,
                    descripcion = responseMonumento.descripcion,
                    imagen = responseMonumento.imagen,
                    descripcionPlus = responseMonumento.descripcionPlus
                )
            } ?: emptyList()
        } catch (e: Exception) {
            Log.e("ERROR LISTAR MONU", "Lista vacia de monumentos")
            emptyList()

        }

    }

    override suspend fun deleteMonumento(pos: Int): Boolean {
        return if(pos < ListMonumentos.ciudades.monumentos.size){
            ListMonumentos.ciudades.monumentos.removeAt(pos)
            true
        }
        else
            false
    }

    override suspend fun addMonumento(monumento: Monumento): Monumento? {
        ListMonumentos.ciudades.monumentos.add(monumento)
        return monumento
    }

    override suspend fun update(pos: Int, monumento: Monumento): Boolean {
        return if (pos < ListMonumentos.ciudades.monumentos.size){
            ListMonumentos.ciudades.monumentos[pos] = ListMonumentos.ciudades.monumentos.get(pos).copy(
                id = monumento.id,
                nombre = monumento.nombre,
                fecha = monumento.fecha,
                ciudad = monumento.ciudad,
                descripcion = monumento.descripcion,
                descripcionPlus = monumento.descripcionPlus
            )
            true
        }
        else
            false
    }

    override suspend fun exisMonumento(monumento: Monumento): Boolean = ListMonumentos.ciudades.monumentos.contains(monumento)

    override suspend fun getMonumentoById(pos: Int): Monumento? {
        return if(pos < ListMonumentos.ciudades.monumentos.size)
            ListMonumentos.ciudades.monumentos.get(pos)
        else
            null
    }

    override fun getMonumentoByPos(pos: Int): Monumento? {
        return if(pos < ListMonumentos.ciudades.monumentos.size)
            ListMonumentos.ciudades.monumentos.get(pos)
        else
            null
    }

}