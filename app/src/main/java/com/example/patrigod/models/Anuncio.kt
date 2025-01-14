package com.example.patrigod.models
//Clase anuncio en la que luego voy a crear una lista inmutable para
//que todos los usuario puedan ver una seccion comun

class Anuncio(var id: Int,
              var nombre : String,
              var ciudad : String,
              var fecha : String,
              var informacion : String,
              var imagen : String)  {
    override fun toString(): String {
        return "Anuncio(id=$id, nombre='$nombre', ciudad='$ciudad', fecha='$fecha', informacion='$informacion', imagen='$imagen')"
    }
}
