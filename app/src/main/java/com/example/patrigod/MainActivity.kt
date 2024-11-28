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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater) //inflamos el binding
        setContentView(binding.root)

        iniciarPreferenciasCompartidas()

        mostrarUsuario()
    }
    private fun iniciarPreferenciasCompartidas(){
        val nombreFicheroCompartido = getString(R.string.nombre_fichero_preferencia_compartida)

        this.fichero_compartido = getSharedPreferences(nombreFicheroCompartido, MODE_PRIVATE)
    }
    private fun mostrarUsuario(){
        val user = intent.getStringExtra("usuario")
        binding.tvUsuario.text = user
    }




}