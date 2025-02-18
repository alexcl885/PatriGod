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
import com.example.patrigod.databinding.DialogEditMonumentoBinding
import com.example.patrigod.domain.monumentos.models.Monumento
import com.example.patrigod.domain.monumentos.models.ArgumentsMonumento
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.OutputStream

class DialogEditMonumento(
    val monumentoToUpdate: Monumento,
    val updateMonumentoDialog: (Monumento) -> Unit
) : DialogFragment() {

    private lateinit var binding: DialogEditMonumentoBinding

    // Códigos de permiso
    private val RESPUESTA_PERMISO_CAMARA = 100
    private val RESPUESTA_PERMISO_ALMACENAMIENTO = 200
    private val RESPUESTA_PERMISO_GALERIA = 300

    // Para almacenar la imagen capturada (thumbnail) o la URI seleccionada
    private var bitmap: Bitmap? = null
    private var savedImageUri: Uri? = null

    private lateinit var inicioActividadCamara: ActivityResultLauncher<Intent>
    private lateinit var inicioActividadLecturaGaleria: ActivityResultLauncher<Intent>

    init {
        // Preparamos el Bundle para precargar los campos con los datos actuales del monumento
        val args = Bundle().apply {
            putString(ArgumentsMonumento.ARGUMENT_ID, monumentoToUpdate.id.toString())
            putString(ArgumentsMonumento.ARGUMENT_NAME, monumentoToUpdate.nombre)
            putString(ArgumentsMonumento.ARGUMENT_CITY, monumentoToUpdate.ciudad)
            putString(ArgumentsMonumento.ARGUMENT_DATE, monumentoToUpdate.fecha)
            putString(ArgumentsMonumento.ARGUMENT_DESCRIPTION, monumentoToUpdate.descripcion)
            putString(ArgumentsMonumento.ARGUMENT_IMAGE, monumentoToUpdate.imagen)
        }
        arguments = args
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Registrar ActivityResultLaunchers
        inicioActividadCamara =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    // Se obtiene el thumbnail de la cámara
                    bitmap = result.data?.extras?.get("data") as? Bitmap
                    binding.imageView.setImageBitmap(bitmap)
                }
            }

        inicioActividadLecturaGaleria =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    savedImageUri = result.data?.data // Obtiene la URI de la imagen seleccionada
                    binding.imageView.setImageURI(savedImageUri) // Muestra la imagen en el ImageView

                    savedImageUri?.let {
                        binding.etFoto.setText(it.toString()) // Actualiza el campo de texto con la URI
                    } ?: Toast.makeText(requireContext(), "Error al obtener la imagen", Toast.LENGTH_SHORT).show()
                }
            }

        return activity?.let { act ->
            val inflater = act.layoutInflater
            binding = DialogEditMonumentoBinding.inflate(inflater)

            // Precargar campos con los datos actuales
            arguments?.let { args ->
                binding.etId.setText(args.getString(ArgumentsMonumento.ARGUMENT_ID))
                binding.etnombre.setText(args.getString(ArgumentsMonumento.ARGUMENT_NAME))
                binding.etciudad.setText(args.getString(ArgumentsMonumento.ARGUMENT_CITY))
                binding.etfecha.setText(args.getString(ArgumentsMonumento.ARGUMENT_DATE))
                binding.etdescripcion.setText(args.getString(ArgumentsMonumento.ARGUMENT_DESCRIPTION))
                binding.etFoto.setText(args.getString(ArgumentsMonumento.ARGUMENT_IMAGE))
                val currentImageUri = args.getString(ArgumentsMonumento.ARGUMENT_IMAGE)
                if (!currentImageUri.isNullOrEmpty()) {
                    binding.imageView.setImageURI(Uri.parse(currentImageUri))
                }
            }

            // Botón para tomar foto con cámara
            binding.btnFoto.setOnClickListener {
                if (checkCameraPermission()) {
                    tomarFotoCamara()
                }
            }

            // Botón para seleccionar imagen desde la galería
            binding.btnGaleria.setOnClickListener {
                if (checkGalleryPermission()) {
                    cargarDesdeGaleria()
                }
            }

            // Botón para guardar la imagen capturada
            binding.btnGuardar.setOnClickListener {
                if (bitmap != null) {
                    if (checkStoragePermission()) {
                        almacenarFotoEnGaleria(bitmap!!)
                    }
                } else if (savedImageUri != null) {
                    Toast.makeText(requireContext(), "Imagen seleccionada desde galería", Toast.LENGTH_SHORT).show()
                    binding.etFoto.setText(savedImageUri.toString())
                } else {
                    Toast.makeText(requireContext(), "Primero selecciona o captura una imagen", Toast.LENGTH_SHORT).show()
                }
            }

            val builder = AlertDialog.Builder(act)
            builder.setView(binding.root)
                .setMessage("Editar Monumento")
                .setPositiveButton("Aceptar") { dialog, _ ->
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
                }
                .create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    /**
     * Almacena la imagen (bitmap) en la galería y actualiza el campo etFoto con la URI resultante.
     */
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
            // Forzar actualización de la galería
            val intent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
            intent.data = it
            requireContext().sendBroadcast(intent)
            // Actualizar el campo etFoto con la URI guardada
            binding.etFoto.setText(it.toString())
            // Limpiar el bitmap, ya se guardó la imagen
            this.bitmap = null
        }
    }

    // Métodos para comprobar permisos
    private fun checkCameraPermission(): Boolean {
        return checkPermission(Manifest.permission.CAMERA, RESPUESTA_PERMISO_CAMARA)
    }

    private fun checkGalleryPermission(): Boolean {
        val permiso = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            Manifest.permission.READ_MEDIA_IMAGES
        } else {
            Manifest.permission.READ_EXTERNAL_STORAGE
        }
        return checkPermission(permiso, RESPUESTA_PERMISO_GALERIA)
    }

    private fun checkStoragePermission(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            true
        } else {
            checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, RESPUESTA_PERMISO_ALMACENAMIENTO)
        }
    }

    private fun checkPermission(permiso: String, requestCode: Int): Boolean {
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

    // Lanza la cámara
    private fun tomarFotoCamara() {
        val intentCamara = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        inicioActividadCamara.launch(intentCamara)
    }

    // Abre la galería
    private fun cargarDesdeGaleria() {
        val intentGaleria = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        inicioActividadLecturaGaleria.launch(intentGaleria)
    }

    /*
        Recupera los datos ingresados por el usuario y devuelve el objeto Monumento actualizado.
    */
    private fun recoverDataLayout(binding: DialogEditMonumentoBinding): Monumento {
        return Monumento(
            binding.etId.text.toString().toIntOrNull() ?: 0,
            binding.etnombre.text.toString(),
            binding.etciudad.text.toString(),
            binding.etfecha.text.toString(),
            binding.etdescripcion.text.toString(),
            binding.etFoto.text.toString(),
            binding.etFoto.text.toString() // Ajusta según tu modelo si necesitas 2 parámetros para la imagen
        )
    }

    // Manejo de respuestas de permisos
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
