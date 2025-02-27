package com.example.patrigod.data.monumentos.network.models.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResponseMonumento(
    @SerializedName("idMonu")
    @Expose
    var idMonu :String,
    @SerializedName("nombre")
    @Expose
    var nombre :String,
    @SerializedName("ciudad")
    @Expose
    var ciudad :String,
    @SerializedName("fecha")
    @Expose
    var fecha :String,
    @SerializedName("descripcion")
    @Expose
    var descripcion :String,
    @SerializedName("imagen")
    @Expose
    var imagen :String,
    @SerializedName("descripcionPlus")
    @Expose
    var descripcionPlus :String,
) {
}