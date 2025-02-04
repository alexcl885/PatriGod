package com.example.patrigod.domain.monumentos.interfaces

import com.example.patrigod.domain.monumentos.models.Anuncio

interface InterfazAnuncio {
    fun getDataAnuncios(): List<Anuncio>
}