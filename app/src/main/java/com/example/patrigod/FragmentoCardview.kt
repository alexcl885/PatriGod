package com.example.patrigod

import Controller
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.patrigod.databinding.FragmentoCardviewBinding

class FragmentoCardview() : Fragment() {
    lateinit var binding: FragmentoCardviewBinding
    lateinit var controller : Controller
    lateinit var activityContext : Context


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentoCardviewBinding.inflate(inflater, container, false)
        activityContext = requireActivity()
        controller = Controller(activityContext, this)
        initRecyclerView()
        controller.initData()
        return binding.root
    }




    private fun initRecyclerView() {
        binding.myRecyclerView.layoutManager = LinearLayoutManager( requireContext() )
    }




}