package com.example.patrigod.dialogues
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.patrigod.databinding.DialogEditMonumentoBinding
import com.example.patrigod.models.Monumento
import com.example.patrigod.objects_models.ArgumentsMonumento
class DialogEditMonumento(
    val monumentoToUpdate: Monumento,
    val updateMonumentoDialog: (Monumento) -> Unit
) : DialogFragment() {
    private val ARGUMENT_NAME: String = ArgumentsMonumento.ARGUMENT_NAME
    private val ARGUMENT_CITY: String = ArgumentsMonumento.ARGUMENT_CITY
    private val ARGUMENT_DATE: String = ArgumentsMonumento.ARGUMENT_DATE
    private val ARGUMENT_DESCRIPTION: String = ArgumentsMonumento.ARGUMENT_DESCRIPTION
    private val ARGUMENT_IMAGE: String = ArgumentsMonumento.ARGUMENT_IMAGE
    init {
        /*
        Preparo el Bundle para pasárselo al Dialog.
        */
        val args = Bundle().apply {
            putString(ARGUMENT_NAME, monumentoToUpdate.nombre)
            putString(ARGUMENT_CITY, monumentoToUpdate.ciudad)
            putString(ARGUMENT_DATE, monumentoToUpdate.fecha)
            putString(ARGUMENT_DESCRIPTION, monumentoToUpdate.descripcion)
            putString(ARGUMENT_IMAGE, monumentoToUpdate.imagen)
        }
        this.arguments = args
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return activity?.let {
            val inflater = requireActivity().layoutInflater
            val binding = DialogEditMonumentoBinding.inflate(inflater)
            arguments?.let { args ->  // seteo los datos iniciales en los campos del dialogo
                binding.etnombre.setText(args.getString(ARGUMENT_NAME))
                binding.etciudad.setText(args.getString(ARGUMENT_CITY))
                binding.etfecha.setText(args.getString(ARGUMENT_DATE))
                binding.etdescripcion.setText(args.getString(ARGUMENT_DESCRIPTION))
                binding.etFoto.setText(args.getString(ARGUMENT_IMAGE))
            }
            val builder = AlertDialog.Builder(requireContext())
            builder.setView(binding.root)
                .setPositiveButton("Aceptar") { dialog, id ->
                    val updateMonumento = recoverDataLayout(binding)

                    if (updateMonumento.nombre.isEmpty() ||
                        updateMonumento.ciudad.isEmpty() ||
                        updateMonumento.fecha.isEmpty() ||
                        updateMonumento.descripcion.isEmpty() ||
                        updateMonumento.imagen.isEmpty()
                    ) {
                        Toast.makeText(requireContext(), "Algún campo está vacío", Toast.LENGTH_LONG).show()
                        dialog.dismiss()
                    } else {
                        updateMonumentoDialog(updateMonumento)

                    }
                }
                .setNegativeButton("Cancelar") { dialog, _ ->
                    dialog.cancel()
                }.create()
        }?: throw IllegalStateException("Activity cannot be null")


    }
    /*
        Método para recuperar los datos ingresados por el usuario.
    */
    private fun recoverDataLayout(binding: DialogEditMonumentoBinding): Monumento {
        return Monumento(
            binding.etId.text.toString().toInt(),
            binding.etnombre.text.toString(),
            binding.etciudad.text.toString(),
            binding.etfecha.text.toString(),
            binding.etdescripcion.text.toString(),
            binding.etFoto.text.toString(),
            binding.etFoto.text.toString()
        )
    }
}
