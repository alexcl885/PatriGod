package com.example.patrigod.models

data class Monumento(var nombre : String,
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