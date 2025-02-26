package com.example.patrigod.data.user.models.Request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RequestUsuarioEntero(
    @SerializedName("dni")
    @Expose
    var dni: String,
    @SerializedName("email")
    @Expose
    var email: String,

    @SerializedName("password")
    @Expose
    var password: String,
    @SerializedName("nombre")
    @Expose
    var nombre: String,
    @SerializedName("phone")
    @Expose
    var phone: String,
    @SerializedName("token")
    @Expose
    var token: String

) {
}