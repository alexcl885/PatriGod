package com.example.patrigod.dialogues

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.patrigod.R
import com.example.patrigod.databinding.DialogAddMonumentoBinding
import com.example.patrigod.models.Monumento

class DialogAddMonumento(
    private val onNewMonumentoDialog: (Monumento) -> Unit
) : DialogFragment() {

    //Por cojones, tengo que devolver un Dialo
    //En caso de que no pueda, porque no hay activity, lanzo excepción.
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let { myActi ->
            val builder = AlertDialog.Builder(myActi)
            val inflater = myActi.layoutInflater;  //objeto que me infla los cojones
            val viewDialog =
                inflater.inflate(R.layout.dialog_add_monumento, null)  //ya tengo la vista.
            /*
            A la plantilla del dialogo que tiene dos botones, le añado arriba mi vista personalizada
             */
            builder.setView(viewDialog)  //le cargo la vista a mi dialogo
            builder.setMessage("Añadir Monumento") //como devuelve el objeto builder, encadeno funciones
                .setPositiveButton("Aceptas?",
                    DialogInterface.OnClickListener {  //lambda que contendrá el código del onclick
                            dialog, id ->
                        val monumento = recoverDataLayout(viewDialog)
                        if (validacion(monumento)) {
                            onNewMonumentoDialog(monumento)
                            Toast.makeText(myActi, "Item creado", Toast.LENGTH_LONG).show()
                        } else {
                            Toast.makeText(
                                myActi,
                                "Rellena todos los campos por favor",
                                Toast.LENGTH_LONG
                            ).show()
                        }

                    }
                ) //el positive me devuelve un objeto Dialo
                .setNegativeButton("Cancelar",
                    DialogInterface.OnClickListener { dialog, id ->
                        Toast.makeText(
                            myActi,
                            "Has cerrado la ventana para añadir",
                            Toast.LENGTH_LONG
                        ).show()
                        dialog.dismiss() //cierro el díalogo y vuelvo a mi lista.
                    }
                )//el negative me devuelve un objeto Dialog
                // Como es la última instrucción dentro del return, es lo que se devuelve
                .create() //Construyo el Dialogo que ya he preconfigurado.


        } ?: throw IllegalStateException("Activity cannot be null")


    }

    private fun validacion(monumento: Monumento): Boolean {
        return monumento.nombre.isNotEmpty() &&
                monumento.ciudad.isNotEmpty() &&
                monumento.fecha.isNotEmpty() &&
                monumento.descripcion.isNotEmpty() &&
                monumento.imagen.isNotEmpty()

    }

    private fun recoverDataLayout(view: View): Monumento {
        val binding = DialogAddMonumentoBinding.bind(view)
        return Monumento(
            binding.etnombre.text.toString(),
            binding.etciudad.text.toString(),
            binding.etfecha.text.toString(),
            binding.etdescripcion.text.toString(),
            binding.etFoto.text.toString()
        )
    }
}
