package com.example.patrigod.data.user.models.Response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResponseUsuario (

    @SerializedName("token")
    @Expose
    var token : String
){
}