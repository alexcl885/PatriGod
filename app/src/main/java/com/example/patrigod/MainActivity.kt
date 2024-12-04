package com.example.patrigod

import Controler

import android.content.SharedPreferences

import android.os.Bundle

import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

import androidx.recyclerview.widget.LinearLayoutManager

import com.example.patrigod.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var fichero_compartido: SharedPreferences
    lateinit var binding: ActivityMainBinding
    private lateinit var fragmentoCardview: FragmentoCardview

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater) //inflamos el binding
        setContentView(binding.root)

        iniciarPreferenciasCompartidas()

        mostrarUsuario()
        createFragmento()
    }
    private fun iniciarPreferenciasCompartidas(){
        val nombreFicheroCompartido = getString(R.string.nombre_fichero_preferencia_compartida)

        this.fichero_compartido = getSharedPreferences(nombreFicheroCompartido, MODE_PRIVATE)
    }
    private fun mostrarUsuario(){
        val user = intent.getStringExtra("usuario")
        binding.tvUsuario.text = user
    }
    private fun createFragmento() {
        fragmentoCardview = FragmentoCardview()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, fragmentoCardview) // Añadimos el fragmento al contenedor
            .commit()
    }




}