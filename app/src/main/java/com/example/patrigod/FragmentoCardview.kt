package com.example.patrigod

import Controller
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.patrigod.databinding.FragmentoCardviewBinding

class FragmentoCardview() : Fragment() {
    lateinit var binding: FragmentoCardviewBinding
    lateinit var controller : Controller
    lateinit var activityContext : Context

    lateinit var navController: NavController


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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val navHost = requireActivity()
            .supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView)


    }




    private fun initRecyclerView() {
        binding.myRecyclerView.layoutManager = LinearLayoutManager( requireContext() )
    }




}