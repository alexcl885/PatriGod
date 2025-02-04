package com.example.patrigod.ui.views.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
/*
* Clase que comprueba si hay un usuario por lo que =>
*   Si hay un usuario registrado manda directamente al main activity
*   Si no hay un usuario te manda al login.
* */
class ComprobacionIncio : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // compruebo si el usuario se ha identificado
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser != null && currentUser.isEmailVerified) {

            val mainIntent = Intent(this, MainActivity::class.java)
            startActivity(mainIntent)
        } else {

            val loginIntent = Intent(this, Login::class.java)
            startActivity(loginIntent)
        }

    }
}