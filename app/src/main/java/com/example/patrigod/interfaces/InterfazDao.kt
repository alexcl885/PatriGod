package com.example.patrigod.interfaces


import com.example.patrigod.models.Monumento

interface InterfazDao  {
    fun getDataMonuments(): List<Monumento>
}