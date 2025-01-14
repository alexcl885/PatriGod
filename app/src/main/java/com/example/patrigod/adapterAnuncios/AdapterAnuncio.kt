package com.example.patrigod.adapterAnuncios

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.patrigod.R
import com.example.patrigod.models.Anuncio
import com.example.patrigod.models.Monumento

class AdapterAnuncio(var listAnuncio: MutableList<Anuncio>)
    : RecyclerView.Adapter<ViewAnuncio>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewAnuncio {
        val layoutInflater = LayoutInflater.from(parent.context)//objeto para crear la vista.
        val layoutItemAnuncio = R.layout.fragment_viewanuncio //accedo al xml del item a crear.
        return ViewAnuncio(layoutInflater.inflate(layoutItemAnuncio, parent, false))
    }

    override fun getItemCount(): Int  = listAnuncio.size

    override fun onBindViewHolder(holder: ViewAnuncio, position: Int) {
        holder.renderize(listAnuncio[position])
    }

}