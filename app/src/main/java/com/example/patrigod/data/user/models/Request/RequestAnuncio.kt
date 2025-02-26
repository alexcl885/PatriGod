package com.example.patrigod.data.user.models.Request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RequestAnuncio(var id: Int,
                    @SerializedName("name")
                    @Expose
                     var name : String ?,
                     var ciudad : String?,
                     var fecha : String?,
                     var informacion : String?,
                     var imagen : String?) {
}