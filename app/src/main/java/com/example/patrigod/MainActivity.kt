package com.example.patrigod

import Controler
import android.content.Intent

import android.content.SharedPreferences

import android.os.Bundle

import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

import androidx.recyclerview.widget.LinearLayoutManager

import com.example.patrigod.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var fichero_compartido: SharedPreferences
    lateinit var binding: ActivityMainBinding
    lateinit var controller : Controler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater) //inflamos el binding
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        iniciarPreferenciasCompartidas()
        init()

        /*
        * Boton para quitar la sesion y volver al login
        * */
        binding.logout.setOnClickListener {
            logout()
        }

    }
    private fun iniciarPreferenciasCompartidas(){
        val nombreFicheroCompartido = getString(R.string.nombre_fichero_preferencia_compartida)

        this.fichero_compartido = getSharedPreferences(nombreFicheroCompartido, MODE_PRIVATE)
    }

    fun init(){
        initRecyclerView()
        controller = Controler(this)
        controller.setAdapter()


    }
    private fun initRecyclerView() {
        binding.myRecyclerView.layoutManager = LinearLayoutManager( this)
    }

    private fun logout() {
        // cierro sesión en Firebase
        auth.signOut()

        // redirigo al LoginActivity
        val loginIntent = Intent(this, Login::class.java)

        /*
        *  Configuro las banderas para limpiar la pila de actividades y evitar que el usuario
        *  pueda regresar a la pantalla principal o a otras actividades anteriores después de cerrar sesión.
        *
        * */
        loginIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(loginIntent)

    }


}