package com.example.patrigod.dialogues
import android.app.AlertDialog
import android.app.Dialog

import android.os.Bundle

import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.patrigod.R

import com.example.patrigod.databinding.FragmentDialogAddMonumentoBinding
import com.example.patrigod.models.Monumento

class DialogAddMonumento(
    private val onNewMonumentoDialog: (Monumento) -> Unit
) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Inflar la vista del diálogo
        val inflater = requireActivity().layoutInflater
        val viewDialog = inflater.inflate(R.layout.fragment_dialog_add_monumento, null)

        // Crear el binding con la vista inflada
        val binding = FragmentDialogAddMonumentoBinding.bind(viewDialog)

        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(viewDialog)
            .setTitle("Añadir Monumento")
            .setPositiveButton("Añadir") { _, _ ->
                val newMonumento = recoverDataLayout(binding)
                if (newMonumento == null) {
                    Toast.makeText(
                        activity,
                        "Por favor, completa todos los campos",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    onNewMonumentoDialog(newMonumento)
                }
            }
            .setNegativeButton("Cancelar") { dialog, _ ->
                dialog.dismiss()
            }

        return builder.create()
    }

    // Recuperar datos del layout y crear el objeto Monumento
    private fun recoverDataLayout(binding: FragmentDialogAddMonumentoBinding): Monumento? {
        val nombre = binding.etnombre.text.toString().trim()
        val ciudad = binding.etciudad.text.toString().trim()
        val fecha = binding.etfecha.text.toString().trim()
        val descripcion = binding.etdescripcion.text.toString().trim()
        val fotoUrl = binding.etFoto.text.toString().trim()

        return if (nombre.isEmpty() || ciudad.isEmpty() || fecha.isEmpty() || descripcion.isEmpty() || fotoUrl.isEmpty()) {
            null
        } else {
            Monumento(
                nombre = nombre,
                ciudad = ciudad,
                fecha = fecha,
                descripcion = descripcion,
                imagen = fotoUrl
            )
        }
    }
}
