package com.example.patrigod.Fragmentos

import Controller
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.patrigod.databinding.FragmentAnunciosBinding
import com.example.patrigod.databinding.FragmentoCardviewBinding


class Anuncios : Fragment() {
    lateinit var binding: FragmentAnunciosBinding
    lateinit var controller: Controller
    lateinit var activityContext: Context

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAnunciosBinding.inflate(inflater, container, false)
        activityContext = requireActivity()

        // Inicializamos el controlador con los fragmentos
        controller = Controller(activityContext, FragmentoCardview(), this)
        controller.initData() // Inicializa los datos del RecyclerView y configuraciones

        return binding.root
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        // El contexto está disponible aquí de forma segura
        controller = Controller(context, FragmentoCardview(), this)
    }
}
