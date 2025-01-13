package com.example.patrigod.dao

import com.example.patrigod.interfaces.InterfazAnuncio
import com.example.patrigod.interfaces.InterfazDao
import com.example.patrigod.models.Anuncio
import com.example.patrigod.models.Monumento
import com.example.patrigod.objects_models.Datos
import com.example.patrigod.objects_models.DatosAnuncios

class AnuncioDAO private constructor(): InterfazAnuncio {
    companion object {
        val myDao: AnuncioDAO by lazy { //lazy delega a un primer acceso
            AnuncioDAO() //Me creo sólo este objeto una vez.
        }
    }

    //Método que accede a la BBDD y devuelve todos los datos
    override fun getDataAnuncios(): List<Anuncio> = DatosAnuncios().listAnuncios
}