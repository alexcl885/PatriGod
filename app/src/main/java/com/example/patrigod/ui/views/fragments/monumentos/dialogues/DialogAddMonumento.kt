package com.example.patrigod.ui.views.fragments.monumentos.dialogues

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.example.patrigod.R
import com.example.patrigod.databinding.DialogAddMonumentoBinding
import com.example.patrigod.domain.monumentos.models.Monumento
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.OutputStream

class DialogAddMonumento(
    private val onNewMonumentoDialog: (Monumento) -> Unit
) : DialogFragment() {

    private lateinit var binding: DialogAddMonumentoBinding

    private val RESPUESTA_PERMISO_CAMARA = 100
    private val RESPUESTA_PERMISO_ALMACENAMIENTO = 200
    private val RESPUESTA_PERMISO_GALERIA = 300

    private var bitmap: Bitmap? = null

    private lateinit var inicioActividadCamara: ActivityResultLauncher<Intent>
    private lateinit var inicioActividadLecturaGaleria: ActivityResultLauncher<Intent>

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        // Registrar los ActivityResultLaunchers
        inicioActividadCamara =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    bitmap = result.data?.extras?.get("data") as? Bitmap
                    binding.imageView.setImageBitmap(bitmap)
                }
            }

        inicioActividadLecturaGaleria =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val imagenUri = result.data?.data
                    binding.imageView.setImageURI(imagenUri)
                }
            }

        return activity?.let { myActi ->
            val builder = AlertDialog.Builder(myActi)
            val inflater = myActi.layoutInflater
            val viewDialog = inflater.inflate(R.layout.dialog_add_monumento, null)
            binding = DialogAddMonumentoBinding.bind(viewDialog)

            binding.btnFoto.setOnClickListener {
                if (compruebaPermisosCamara()) {
                    tomarFotoCamara()
                }
            }

            binding.btnGaleria.setOnClickListener {
                if (compruebaPermisosLecturaGaleria()) {
                    cargarDesdeGaleria()
                }
            }

            binding.btnGuardar.setOnClickListener {
                bitmap?.let {
                    if (compruebaPermisosAlmacenamiento()) {
                        almacenarFotoEnGaleria(it)
                    }
                } ?: Toast.makeText(requireContext(), "Primero captura o selecciona una imagen", Toast.LENGTH_SHORT).show()
            }

            builder.setView(viewDialog)
                .setMessage("Añadir Monumento")
                .setPositiveButton("Aceptar") { dialog, id ->
                    val monumento = recoverDataLayout(viewDialog)
                    if (validacion(monumento)) {
                        onNewMonumentoDialog(monumento)
                        Toast.makeText(myActi, "Item creado", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(myActi, "Rellena todos los campos por favor", Toast.LENGTH_LONG).show()
                    }
                }
                .setNegativeButton("Cancelar") { dialog, id ->
                    Toast.makeText(myActi, "Has cerrado la ventana para añadir", Toast.LENGTH_LONG).show()
                    dialog.dismiss()
                }
                .create()

        } ?: throw IllegalStateException("Activity cannot be null")
    }

    private fun almacenarFotoEnGaleria(bitmap: Bitmap) {
        val nombreArchivo = System.currentTimeMillis().toString() + ".jpg"
        var fos: OutputStream? = null
        var imageUri: Uri? = null

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val resolver = requireContext().contentResolver
            val values = ContentValues().apply {
                put(MediaStore.Images.Media.DISPLAY_NAME, nombreArchivo)
                put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
                put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES + "/AppPruebaCamara")
                put(MediaStore.Images.Media.IS_PENDING, 1)
            }

            imageUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)

            try {
                fos = imageUri?.let { resolver.openOutputStream(it) }
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }

            values.clear()
            values.put(MediaStore.Images.Media.IS_PENDING, 0)
            imageUri?.let { resolver.update(it, values, null, null) }
        } else {
            val directorioImagenes = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString()
            val archivoImagen = File(directorioImagenes, nombreArchivo)
            try {
                fos = FileOutputStream(archivoImagen)
                imageUri = Uri.fromFile(archivoImagen)
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }
        }

        fos?.use {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
            Toast.makeText(requireContext(), "Imagen guardada", Toast.LENGTH_SHORT).show()
        }

        imageUri?.let {
            val intent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
            intent.data = it
            requireContext().sendBroadcast(intent)
        }
    }

    private fun compruebaPermisosCamara(): Boolean {
        return compruebaPermiso(Manifest.permission.CAMERA, RESPUESTA_PERMISO_CAMARA)
    }

    private fun compruebaPermisosLecturaGaleria(): Boolean {
        // Para Android 13+ usamos READ_MEDIA_IMAGES; en versiones anteriores, READ_EXTERNAL_STORAGE
        val permiso = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            Manifest.permission.READ_MEDIA_IMAGES
        } else {
            Manifest.permission.READ_EXTERNAL_STORAGE
        }
        return compruebaPermiso(permiso, RESPUESTA_PERMISO_GALERIA)
    }

    private fun compruebaPermisosAlmacenamiento(): Boolean {
        // Para Android Q y superiores no es necesario solicitar WRITE_EXTERNAL_STORAGE
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            true
        } else {
            compruebaPermiso(Manifest.permission.WRITE_EXTERNAL_STORAGE, RESPUESTA_PERMISO_ALMACENAMIENTO)
        }
    }

    private fun compruebaPermiso(permiso: String, requestCode: Int): Boolean {
        context?.let {
            if (ContextCompat.checkSelfPermission(it, permiso) == PackageManager.PERMISSION_GRANTED) {
                return true
            } else {
                requestPermissions(arrayOf(permiso), requestCode)
                return false
            }
        }
        return false
    }

    private fun tomarFotoCamara() {
        val intentCamara = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        inicioActividadCamara.launch(intentCamara)
    }

    private fun cargarDesdeGaleria() {
        val intentGaleria = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        inicioActividadLecturaGaleria.launch(intentGaleria)
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
            binding.etId.text.toString().toInt(),
            binding.etnombre.text.toString(),
            binding.etciudad.text.toString(),
            binding.etfecha.text.toString(),
            binding.etdescripcion.text.toString(),
            binding.etFoto.text.toString(),
            binding.etFoto.text.toString()
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            RESPUESTA_PERMISO_CAMARA -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    tomarFotoCamara()
                } else {
                    Toast.makeText(requireContext(), "Permiso de cámara denegado", Toast.LENGTH_SHORT).show()
                }
            }
            RESPUESTA_PERMISO_ALMACENAMIENTO -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    bitmap?.let { almacenarFotoEnGaleria(it) }
                } else {
                    Toast.makeText(requireContext(), "Permiso de almacenamiento denegado", Toast.LENGTH_SHORT).show()
                }
            }
            RESPUESTA_PERMISO_GALERIA -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    cargarDesdeGaleria()
                } else {
                    Toast.makeText(requireContext(), "Permiso de galería denegado", Toast.LENGTH_LONG).show()
                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}
