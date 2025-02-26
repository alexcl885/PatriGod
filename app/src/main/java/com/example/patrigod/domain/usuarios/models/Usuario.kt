package com.example.patrigod.domain.usuarios.models

data class Usuario
    (var id: Int,
    var dni : String,
    var email : String,
    var password : String,
    var nombre : String,
    var phone : String,
     var token : String
    ) {
}