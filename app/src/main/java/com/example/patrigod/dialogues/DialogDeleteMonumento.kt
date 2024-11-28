package com.example.patrigod.dialogues

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.patrigod.R
import com.example.patrigod.models.Monumento

class DialogDeleteMonumento(
    val pos: Int, //position of monumento
    val onDeleteHotelDialog: (Int) -> Unit
) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let { myActy ->
            val builder = AlertDialog.Builder(myActy)
            val inflater = myActy.layoutInflater
            val viewDialog = inflater.inflate(R.layout.dialog_delete_monumento, null)
            builder.setView(viewDialog) // Le cargo la vista a mi diálogo
            builder.setMessage("¿Estás seguro que quieres eliminar el monumento")
                .setPositiveButton("SI") { dialog, id ->
                    onDeleteHotelDialog(pos)
                }
                .setNegativeButton("Cancelar") { dialog, id ->
                    dialog.cancel()
                }
                .create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}
