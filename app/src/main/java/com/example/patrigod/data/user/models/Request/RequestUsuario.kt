package com.example.patrigod.data.user.models.Request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RequestUsuario(
    @SerializedName("dni")
    @Expose
    var dni: String,

    @SerializedName("password")
    @Expose
    var password: String
)
