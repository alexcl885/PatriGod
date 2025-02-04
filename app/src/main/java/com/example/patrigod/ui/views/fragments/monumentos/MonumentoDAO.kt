package com.example.patrigod.ui.views.fragments.monumentos


import com.example.patrigod.domain.monumentos.interfaces.InterfazDao
import com.example.patrigod.domain.monumentos.models.Monumento
import com.example.patrigod.data.monumentos.objects_models.RepositoryObjects


class MonumentoDAO private constructor() : InterfazDao {
    companion object {
        val myDao: MonumentoDAO by lazy { //lazy delega a un primer acceso
            MonumentoDAO() //Me creo sólo este objeto una vez.
        }
    }

    //Método que accede a la BBDD y devuelve todos los datos
    override
    fun getDataMonuments(): List<Monumento> = RepositoryObjects.listMonumentos

}