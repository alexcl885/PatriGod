package com.example.patrigod.ui.views.fragments.monumentos

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.patrigod.databinding.FragmentoCardviewBinding
import com.example.patrigod.domain.monumentos.models.Monumento
import com.example.patrigod.ui.views.fragments.monumentos.adapter.AdapterMonumento
import com.example.patrigod.ui.views.fragments.monumentos.dialogues.DialogAddMonumento
import com.example.patrigod.ui.views.fragments.monumentos.dialogues.DialogDeleteMonumento
import com.example.patrigod.ui.views.fragments.monumentos.dialogues.DialogEditMonumento
import com.example.patrigod.ui.viewModel.monumentos.MonumentoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentoMonumento : Fragment() {
    private lateinit var binding: FragmentoCardviewBinding
    private val monumentoViewModel: MonumentoViewModel by viewModels()
    private lateinit var layoutManager: LinearLayoutManager
    lateinit var adapterMonumento: AdapterMonumento

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentoCardviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        layoutManager = LinearLayoutManager(activity)
        binding.myRecyclerView.layoutManager = layoutManager
        setAdapter(mutableListOf())
        setObservers()
        monumentoViewModel.showMonumentos()  // Cargo la lista inicial
        btnAddOnClickListener()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setObservers() {
        monumentoViewModel.monumentosLiveData.observe(viewLifecycleOwner, Observer { monumentos ->
            adapterMonumento.listMonumentos = monumentos.toMutableList()
            adapterMonumento.notifyDataSetChanged()  // Refresca toda la lista
        })

        // Puedes eliminar el observer de newMonumentoLiveData si no lo vas a utilizar
         monumentoViewModel.newMonumentoLiveData.observe(viewLifecycleOwner, Observer { newMonumento ->
            newMonumento?.let {
                adapterMonumento.listMonumentos.add(it)
                adapterMonumento.notifyItemInserted(adapterMonumento.listMonumentos.size - 1)
                layoutManager.scrollToPosition(adapterMonumento.listMonumentos.size - 1)
            }
        })

        monumentoViewModel.posDeleteHotelLiveDate.observe(viewLifecycleOwner, Observer { positionToDelete ->
            if (positionToDelete in adapterMonumento.listMonumentos.indices) {
                adapterMonumento.listMonumentos.removeAt(positionToDelete)
                adapterMonumento.notifyItemRemoved(positionToDelete)
            }
        })

        monumentoViewModel.posUpdateMonumentoLiveData.observe(viewLifecycleOwner, Observer { positionToUpdate ->
            val updatedItem = monumentoViewModel.monumentosLiveData.value?.getOrNull(positionToUpdate)
            updatedItem?.let {
                adapterMonumento.listMonumentos[positionToUpdate] = it
                adapterMonumento.notifyItemChanged(positionToUpdate)
            }
        })
    }

    private fun setAdapter(monumentos: MutableList<Monumento>) {
        adapterMonumento = AdapterMonumento(
            monumentos,
            { pos -> deleteMonumento(pos) },
            { pos -> updateMonumento(pos) },
            { pos -> navigateToDetails(pos) }
        )
        binding.myRecyclerView.adapter = adapterMonumento
    }

    private fun btnAddOnClickListener() {
        binding.btnAdd.setOnClickListener {
            Toast.makeText(context, "Añadiremos un nuevo monumento!", Toast.LENGTH_LONG).show()
            val dialog = DialogAddMonumento { monumento ->
                monumentoViewModel.addMonumento(monumento)
            }
            dialog.show(requireActivity().supportFragmentManager, "Añadimos un nuevo monumento")
        }
    }

    private fun navigateToDetails(pos: Int) {
        if (pos in adapterMonumento.listMonumentos.indices) {
            val monumentoId = adapterMonumento.listMonumentos[pos].id
            findNavController().navigate(
                FragmentoMonumentoDirections.actionFragmentoCardviewToDetallesFragment(
                    idItem = monumentoId
                )
            )
        }
    }

    fun deleteMonumento(pos: Int) {
        if (pos in adapterMonumento.listMonumentos.indices) {
            val dialog = DialogDeleteMonumento(pos) {
                monumentoViewModel.deleteMonumento(it)
            }
            dialog.show(requireActivity().supportFragmentManager, "Borraremos el monumento")
        } else {
            Toast.makeText(context, "Error: Índice fuera de rango", Toast.LENGTH_SHORT).show()
        }
    }

    fun updateMonumento(pos: Int) {
        if (pos in adapterMonumento.listMonumentos.indices) {
            val monumento = adapterMonumento.listMonumentos[pos]
            val editDialog = DialogEditMonumento(monumento) { editMonumento ->
                monumentoViewModel.updateMonumento(pos, editMonumento)
            }
            editDialog.show(requireActivity().supportFragmentManager, "Editamos un monumento!!")
        } else {
            Toast.makeText(context, "Error: Índice fuera de rango", Toast.LENGTH_SHORT).show()
        }
    }
}
