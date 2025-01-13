package com.example.patrigod.interfaces

import com.example.patrigod.models.Anuncio
import com.example.patrigod.models.Monumento

interface InterfazAnuncio {
    fun getDataAnuncios(): List<Anuncio>
}