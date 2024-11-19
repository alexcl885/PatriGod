package com.example.patrigod.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.patrigod.R
import com.example.patrigod.models.Monumento

class AdapterMonumento(var listMonumentos: MutableList<Monumento>) :
    RecyclerView.Adapter<ViewMonumento>() {
    /*
       Método que crea la view del ViewHolderMonumento
    */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewMonumento {
        val layoutInflater = LayoutInflater.from(parent.context)//objeto para crear la vista.
        val layoutItemHotel = R.layout.activity_view //accedo al xml del item a crear.
        return ViewMonumento(layoutInflater.inflate(layoutItemHotel, parent, false))
    }
    /*
        Este método, debe renderizar todos los datos o propiedades de cada monumento con la view.
        Accedemos al objeto por medio de position
    */

    override fun onBindViewHolder(holder: ViewMonumento, position: Int) {
        holder.renderize(listMonumentos.get(position))
    }
    /*
        Este método, devuelve el número de objetos a representar en el recyclerView.
     */

    override fun getItemCount(): Int = listMonumentos.size



}