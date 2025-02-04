package com.example.patrigod.domain.monumentos.models

data class Monumento(var id: Int,
                     var nombre : String,
                     var ciudad : String,
                     var fecha : String,
                     var descripcion : String,
                     var imagen : String,
                     var descripcionPlus : String) {

    //Metodo toString
    override fun toString(): String {
        return "Monumento(nombre='$nombre', ciudad='$ciudad', fecha=$fecha, descripcion='$descripcion', imagen='$imagen', descripcionPlus='$descripcionPlus')"
    }
}