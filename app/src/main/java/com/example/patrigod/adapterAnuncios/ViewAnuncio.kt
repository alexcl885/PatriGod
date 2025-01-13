package com.example.patrigod.adapterAnuncios

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.patrigod.databinding.ActivityViewBinding
import com.example.patrigod.databinding.FragmentAnunciosBinding
import com.example.patrigod.databinding.FragmentViewanuncioBinding
import com.example.patrigod.models.Anuncio
import com.example.patrigod.models.Monumento

class ViewAnuncio( view: View) : RecyclerView.ViewHolder(view) {
    var binding: FragmentViewanuncioBinding

    init {
        binding = FragmentViewanuncioBinding.bind(view)

    }
    fun renderize(anuncio: Anuncio) {
        binding.tvNombre.setText(anuncio.nombre)
        binding.tvCiudad.setText(anuncio.ciudad)
        binding.tvFecha.setText(anuncio.fecha)
        binding.tvInformacion.setText(anuncio.informacion)
        Glide
            .with(itemView.context)
            .load(anuncio.imagen)
            .centerCrop()
            .into(binding.ivFoto)


    }

}