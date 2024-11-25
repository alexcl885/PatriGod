package com.example.patrigod.controler

import android.content.Context
import android.os.Build
import android.util.Log
import android.widget.Adapter
import android.widget.Toast

import com.example.patrigod.MainActivity
import com.example.patrigod.adapter.AdapterMonumento
import com.example.patrigod.dao.MonumentoDAO
import com.example.patrigod.models.Monumento

class Controler
    (val context: Context) {
    lateinit var listMonumentos: MutableList<Monumento> //lista de objetos
    private lateinit var adapter: AdapterMonumento

    init {
        initData()
        loggOut()

    }


    fun initData() {
        // listHotels = DaoHotels2.myDao.toMutableList()
        listMonumentos =
            MonumentoDAO.myDao.getDataMonuments().toMutableList() //llamamos al singleton.
    }

    fun loggOut() {
        Toast.makeText(context, "He mostrado los datos en pantalla", Toast.LENGTH_LONG).show()
        listMonumentos.forEach {
            println(it)
        }
    }

    fun setAdapter() { // Cargamos nuestro AdapterHotgel al adapter del RecyclerView
        val myActivity = context as MainActivity
        adapter =
            AdapterMonumento(listMonumentos,
                { pos ->
                    deleteMonumento(pos)
                }, { pos ->
                    updateMonumento(pos)
                })
        myActivity.binding.myRecyclerView.adapter = adapter
    }

    fun deleteMonumento(pos: Int) {
        val myActivity = context as MainActivity
        if (pos >= 0 && pos < listMonumentos.size) {
            // Elimino el elemento de la lista
            listMonumentos.removeAt(pos)
            // Notifico al adaptador del cambio
            myActivity.binding.myRecyclerView.adapter?.apply {
                notifyItemRemoved(pos) // notifico la eliminación del elemento
                notifyItemRangeChanged(
                    pos,
                    listMonumentos.size - pos
                ) // actualizo las posiciones restantes
            }

            Toast.makeText(
                context,
                "Se eliminó el monumento en la posición $pos",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            Toast.makeText(
                context,
                "Índice fuera de rango: $pos, tamaño de la lista: ${listMonumentos.size}",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    fun updateMonumento(pos: Int) {

    }
}