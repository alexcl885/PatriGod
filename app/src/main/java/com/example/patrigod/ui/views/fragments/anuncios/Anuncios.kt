package com.example.patrigod.ui.views.fragments.anuncios

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.patrigod.ui.views.fragments.anuncios.adapterAnuncios.AdapterAnuncio

import com.example.patrigod.databinding.FragmentAnunciosBinding
import com.example.patrigod.domain.monumentos.models.Anuncio


class Anuncios : Fragment() {
    lateinit var binding: FragmentAnunciosBinding
    private lateinit var listAnuncios: MutableList<Anuncio>
    lateinit var adapterAnuncio: AdapterAnuncio


    private var layoutManagerAnuncio: LinearLayoutManager = LinearLayoutManager(context)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAnunciosBinding.inflate(inflater, container, false)
        // Inicializamos el controlador con los fragmentos
        initData()
        return binding.root
    }

    fun initData(){
        listAnuncios = AnuncioDAO.myDao.getDataAnuncios().toMutableList()
        setAdapter()

    }
    fun setAdapter() {
        adapterAnuncio = AdapterAnuncio(listAnuncios)
        binding.anuncios.layoutManager = layoutManagerAnuncio
        binding.anuncios.adapter = adapterAnuncio
    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding.anuncios.adapter = null
        binding.anuncios.layoutManager = null
    }



}
