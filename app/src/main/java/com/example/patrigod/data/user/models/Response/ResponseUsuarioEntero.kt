package com.example.patrigod.data.user.models.Response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ResponseUsuarioEntero(
    @SerializedName("msg")
    @Expose
    var msg: String
) {
}