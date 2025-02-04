package com.example.patrigod.ui.views.fragments.anuncios.adapterAnuncios

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.patrigod.databinding.FragmentViewanuncioBinding
import com.example.patrigod.domain.monumentos.models.Anuncio

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