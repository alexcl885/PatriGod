package com.example.patrigod.data.monumentos.repository

import com.example.patrigod.data.monumentos.objects_models.RepositoryObjects
import com.example.patrigod.domain.monumentos.interfaces.InterfaceDao
import com.example.patrigod.domain.monumentos.models.ListMonumentos
import com.example.patrigod.domain.monumentos.models.Monumento
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MonumentoRepository @Inject constructor(): InterfaceDao {
    override suspend fun getNativeMonumentos(): List<Monumento> = RepositoryObjects.listMonumentos

    fun getMonumentos() : List<Monumento> = ListMonumentos.ciudades.monumentos

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