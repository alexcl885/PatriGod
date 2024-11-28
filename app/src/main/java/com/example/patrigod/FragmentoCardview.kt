package com.example.patrigod

import Controler
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatActivity.MODE_PRIVATE
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.patrigod.databinding.ActivityMainBinding
import com.example.patrigod.databinding.FragmentoCardviewBinding

class FragmentoCardview : Fragment() {
    private lateinit var fichero_compartido: SharedPreferences
    lateinit var binding: FragmentoCardviewBinding
    lateinit var controller : Controler


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentoCardviewBinding.inflate(layoutInflater) //inflamos el binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        iniciarPreferenciasCompartidas()

        controller = Controler(activity as MainActivity)
        controller.setAdapter()
        init()
    }
    fun init(){
        initRecyclerView()
    }
    private fun initRecyclerView() {
        binding.myRecyclerView.layoutManager = LinearLayoutManager( activity)
    }

    private fun iniciarPreferenciasCompartidas() {
        val nombreFicheroCompartido = getString(R.string.nombre_fichero_preferencia_compartida)
        fichero_compartido = requireActivity().getSharedPreferences(nombreFicheroCompartido, MODE_PRIVATE)
    }


}