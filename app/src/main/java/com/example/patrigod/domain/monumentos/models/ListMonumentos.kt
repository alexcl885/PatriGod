package com.example.patrigod.domain.monumentos.models

class ListMonumentos private constructor(){
    var monumentos : MutableList<Monumento> = mutableListOf()


    fun getSize() = monumentos.size

    fun getLastPos() = monumentos.size - 1

    companion object{
        val ciudades : ListMonumentos by lazy {
            ListMonumentos()
        }
    }
}