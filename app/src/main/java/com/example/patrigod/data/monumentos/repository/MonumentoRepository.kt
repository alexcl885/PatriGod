package com.example.patrigod.data.monumentos.repository

import android.annotation.SuppressLint
import android.content.Context.MODE_PRIVATE
import android.util.Log
import com.example.patrigod.data.monumentos.network.models.request.RequestMonumento
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
    @SuppressLint("SuspiciousIndentation")
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



    @SuppressLint("SuspiciousIndentation")
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
        return try {
            var monumentoId = getMonumentos()[pos].idMonu
            val response = apiServiceMonumento.deleteMonumentoService(User.token.toString(), monumentoId)

            if (response.isSuccess) {
                Log.e("Monumento Borrado", "Se elimino perfectamente el monumento")
                true
            } else {
                Log.e("ERROR DELETE MONU", "No se pudo eliminar el monumento: ${response.exceptionOrNull()}")
                false
            }

        } catch (e: Exception) {
            Log.e("ERROR DELETE MONU", "Excepción al eliminar el monumento", e)
            false
        }
    }


    override suspend fun addMonumento(monumento: Monumento): Monumento? {
        return try {
            val requestMonumento = RequestMonumento(
                idMonu = monumento.idMonu,
                nombre = monumento.nombre,
                ciudad = monumento.ciudad,
                fecha = monumento.fecha,
                descripcion = monumento.descripcion,
                imagen = monumento.imagen,
                descripcionPlus = monumento.descripcionPlus
            )

            val response = apiServiceMonumento.postMonumentoService(User.token.toString(), requestMonumento)

            if (response.isSuccess) {
                response.getOrNull()?.let { responseMonumento ->
                    val nuevoMonumento = Monumento(
                        id = responseMonumento.idMonu.hashCode(), // Se usa hashCode() ya que id es Int
                        idMonu = responseMonumento.idMonu,
                        nombre = responseMonumento.nombre,
                        ciudad = responseMonumento.ciudad,
                        fecha = responseMonumento.fecha,
                        descripcion = responseMonumento.descripcion,
                        imagen = responseMonumento.imagen,
                        descripcionPlus = responseMonumento.descripcionPlus
                    )

                    ListMonumentos.ciudades.monumentos.add(nuevoMonumento)
                    nuevoMonumento
                }
            } else {
                Log.e("ERROR ADD MONU", "No se pudo agregar el monumento: ${response.exceptionOrNull()}")
                null
            }
        } catch (e: Exception) {
            Log.e("ERROR ADD MONU", "Excepción al agregar el monumento", e)
            null
        }
    }


    override suspend fun update(pos: Int, monumento: Monumento): Boolean {
        return try {
            val requestMonumento = RequestMonumento(
                idMonu = monumento.idMonu,
                nombre = monumento.nombre,
                ciudad = monumento.ciudad,
                fecha = monumento.fecha,
                descripcion = monumento.descripcion,
                imagen = monumento.imagen,
                descripcionPlus = monumento.descripcionPlus
            )
            var idMonumento = getMonumentos()[pos].idMonu

            val response = apiServiceMonumento.patchMonumentoService(User.token.toString(), idMonumento, requestMonumento)

            if (response.isSuccess) {
                response.getOrNull()?.let { responseMonumento ->
                    val updatedMonumento = Monumento(
                        id = responseMonumento.idMonu.hashCode(),
                        idMonu = responseMonumento.idMonu,
                        nombre = responseMonumento.nombre,
                        ciudad = responseMonumento.ciudad,
                        fecha = responseMonumento.fecha,
                        descripcion = responseMonumento.descripcion,
                        imagen = responseMonumento.imagen,
                        descripcionPlus = responseMonumento.descripcionPlus
                    )

                    // Obtener la lista de monumentos y actualizar la posición indicada
                    val monumentos = getMonumentos().toMutableList()
                    if (pos in monumentos.indices) {
                        monumentos[pos] = updatedMonumento
                        return true
                    } else {
                        Log.e("ERROR UPDATE MONU", "Posición fuera de rango: $pos")
                        return false
                    }
                } ?: false
            } else {
                Log.e("ERROR UPDATE MONU", "Error en la API: ${response.exceptionOrNull()}")
                false
            }
        } catch (e: Exception) {
            Log.e("ERROR UPDATE MONU", "Excepción al actualizar el monumento", e)
            false
        }
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