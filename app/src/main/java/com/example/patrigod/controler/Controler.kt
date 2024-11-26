package com.example.patrigod.controler

import android.content.Context
import android.os.Build
import android.util.Log
import android.widget.Adapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.patrigod.MainActivity
import com.example.patrigod.adapter.AdapterMonumento
import com.example.patrigod.dao.MonumentoDAO
import com.example.patrigod.dialogues.DialogAddMonumento
import com.example.patrigod.models.Monumento

class Controler
    (val context: Context) {
    lateinit var listMonumentos: MutableList<Monumento> //lista de objetos
    private lateinit var adapter: AdapterMonumento
    private lateinit var layoutManager : LinearLayoutManager

    init {
        initData()
        loggOut()
        initOnClickListener()

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
    private fun initOnClickListener() {
        val myActivity = context as MainActivity
        myActivity. binding.btnAdd.setOnClickListener {
            addMonumento() //lambda que trata el evento del botón añadir. Inicia el Dialogo
        }
    }

    fun updateMonumento(pos: Int) {

    }
    fun addMonumento(){
        Toast.makeText( context, "Añadiremos un nuevo hotel", Toast.LENGTH_LONG).show()
        val dialog = DialogAddMonumento() {
                monumento -> okOnNewHotel(monumento)
        }
        val myActivity = context as MainActivity
        dialog.show(myActivity. supportFragmentManager, "Añadimos un nuevo hotel")
    }
    private fun okOnNewHotel(monumento: Monumento) {
        listMonumentos.add(listMonumentos.size, monumento)
        adapter.notifyItemInserted( listMonumentos.lastIndex) //notificamos.
        /*
        Lo que hacemos es al insertar un nuevo hotel, de la última posición del
       scroll (ultimo pueblo)
        hacemos un desplazamiento de 20 para que veamos el nuevo pueblo.
        */
        layoutManager.scrollToPositionWithOffset( listMonumentos.lastIndex, 20)
    }
}