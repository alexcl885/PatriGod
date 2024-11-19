package com.example.patrigod.controler

import android.content.Context
import android.os.Build
import android.widget.Toast

import com.example.patrigod.MainActivity
import com.example.patrigod.adapter.AdapterMonumento
import com.example.patrigod.dao.MonumentoDAO
import com.example.patrigod.models.Monumento

class Controler
    (val context: Context) {
    lateinit var listMonumentos: MutableList<Monumento> //lista de objetos

    init {
        initData()
        loggOut()
    }


    fun initData() {
        // listHotels = DaoHotels2.myDao.toMutableList()
        listMonumentos = MonumentoDAO.myDao.getDataMonuments().toMutableList() //llamamos al singleton.
    }

    fun loggOut() {
        Toast.makeText(context, "He mostrado los datos en pantalla", Toast.LENGTH_LONG).show()
        listMonumentos.forEach {
            println(it)
        }
    }

    fun setAdapter() { // Cargamos nuestro AdapterHotgel al adapter del RecyclerView
        val myActivity = context as MainActivity
        myActivity.binding.myRecyclerView.adapter =
            AdapterMonumento(listMonumentos) // Cargamos el Adapter que creamos.
    }
}