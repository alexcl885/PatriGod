package com.example.patrigod.dao


import com.example.patrigod.interfaces.InterfazDao
import com.example.patrigod.models.Monumento
import com.example.patrigod.objects_models.Datos


class MonumentoDAO private constructor() : InterfazDao {
    companion object {
        val myDao: MonumentoDAO by lazy { //lazy delega a un primer acceso
            MonumentoDAO() //Me creo sólo este objeto una vez.
        }
    }

    //Método que accede a la BBDD y devuelve todos los datos
    override
    fun getDataMonuments(): List<Monumento> = Datos().listMonumentos

}