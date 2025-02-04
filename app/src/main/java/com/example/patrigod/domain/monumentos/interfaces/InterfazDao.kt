package com.example.patrigod.domain.monumentos.interfaces


import com.example.patrigod.domain.monumentos.models.Monumento

interface InterfazDao  {
    fun getDataMonuments(): List<Monumento>
}