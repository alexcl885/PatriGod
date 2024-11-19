package com.example.patrigod.dao


import com.example.patrigod.models.Monumento

interface InterfazDao  {
    fun getDataMonuments(): List<Monumento>
}