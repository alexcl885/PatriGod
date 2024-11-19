package com.example.patrigod

import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.patrigod.controler.Controler
import com.example.patrigod.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var fichero_compartido: SharedPreferences
    lateinit var binding: ActivityMainBinding
    lateinit var controller : Controler



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater) //inflamos el binding
        setContentView(binding.root)

        iniciarPreferenciasCompartidas()
        init()
        //mostrarUsuario()



    }
    private fun iniciarPreferenciasCompartidas(){
        val nombreFicheroCompartido = getString(R.string.nombre_fichero_preferencia_compartida)

        this.fichero_compartido = getSharedPreferences(nombreFicheroCompartido, MODE_PRIVATE)
    }
    /*private fun mostrarUsuario(){
        val user = intent.getStringExtra("usuario")
        binding.tvUsuario.text = user
    }*/

    fun init(){
        initRecyclerView()
        controller = Controler(this)
        controller.setAdapter()


    }
    private fun initRecyclerView() {
        binding.myRecyclerView.layoutManager = LinearLayoutManager( this)
    }


}