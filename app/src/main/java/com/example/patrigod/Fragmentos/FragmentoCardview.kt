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
import com.example.patrigod.databinding.FragmentoCardviewBinding

class FragmentoCardview : Fragment() {
    lateinit var binding: FragmentoCardviewBinding
    lateinit var controller: Controller

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentoCardviewBinding.inflate(inflater, container, false)
        controller = Controller(requireActivity(), this) // Enlazamos con el fragmento Anuncios
        controller.initData()

        return binding.root
    }

}