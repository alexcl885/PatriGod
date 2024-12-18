package com.example.patrigod.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.example.patrigod.databinding.ActivityViewBinding


import com.example.patrigod.models.Monumento

class ViewMonumento(
    view: View,
    var deleteOnClick: (Int) -> Unit,
    var updateOnClick: (Int) -> Unit

) : RecyclerView.ViewHolder(view) {
    var binding: ActivityViewBinding

    init {
        binding = ActivityViewBinding.bind(view)



    }


    fun renderize(monumento: Monumento, position: Int) {
        binding.tvNombre.setText(monumento.nombre)
        binding.tvCiudad.setText(monumento.ciudad)
        binding.tvFecha.setText(monumento.fecha)
        binding.tvDescripcion.setText(monumento.descripcion)
        Glide
            .with(itemView.context)
            .load(monumento.imagen)
            .centerCrop()
            .into(binding.ivFoto)

        setOnClickListener(position)
    }

    private fun setOnClickListener(position : Int) {
        binding.ivActualizar.setOnClickListener {
            updateOnClick(position )
        }
        binding.ivBorrar.setOnClickListener {
            deleteOnClick(position)
        }
    }
}

