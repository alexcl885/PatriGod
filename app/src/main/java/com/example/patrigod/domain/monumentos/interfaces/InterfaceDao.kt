package com.example.patrigod.domain.monumentos.interfaces

import com.example.patrigod.domain.monumentos.models.Monumento

/**
 * Lleva suspend ya que estas llamadas
 * se realizaran a traves de corrutinas
 * ya que entramos en asincronia por lo que
 * no sabemos cuanto va a tardar...
 * */
interface InterfaceDao {
    suspend fun getNativeHotels(): List<Monumento>
    suspend fun deleteMonumento(pos:Int): Boolean
    suspend fun addMonumento(monumento: Monumento): Monumento?
    suspend fun  update(pos:Int, monumento: Monumento): Boolean
    suspend fun exisMonumento(monumento: Monumento): Boolean
    suspend fun getMonumentoById(pos: Int): Monumento?
    fun getMonumentoByPos(pos: Int): Monumento?
}