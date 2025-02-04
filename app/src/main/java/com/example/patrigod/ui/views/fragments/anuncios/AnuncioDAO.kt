package com.example.patrigod.ui.views.fragments.anuncios

import com.example.patrigod.domain.monumentos.interfaces.InterfazAnuncio
import com.example.patrigod.domain.monumentos.models.Anuncio
import com.example.patrigod.data.monumentos.objects_models.DatosAnuncios

class AnuncioDAO private constructor(): InterfazAnuncio {
    companion object {
        val myDao: AnuncioDAO by lazy { //lazy delega a un primer acceso
            AnuncioDAO() //Me creo sólo este objeto una vez.
        }
    }

    //Método que accede a la BBDD y devuelve todos los datos
    override fun getDataAnuncios(): List<Anuncio> = DatosAnuncios().listAnuncios
}